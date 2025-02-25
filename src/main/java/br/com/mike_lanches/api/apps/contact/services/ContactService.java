package br.com.mike_lanches.api.apps.contact.services;

// Imports
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.mike_lanches.api.apps.contact.dto.ContactInsertDTO;
import br.com.mike_lanches.api.apps.contact.models.ContactModel;
import br.com.mike_lanches.api.apps.contact.repositories.ContactRepository;
import br.com.mike_lanches.api.core.mail.services.EmailService;
import br.com.mike_lanches.api.shared.dto.ResponseMessageDTO;
import jakarta.validation.Validator;


// Create a new service by contact
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EmailService emailService;
    private final Validator validator;

    // Instance validator
    public ContactService(Validator validator) {
        this.validator = validator;
    }

    // Method that registers the contact in the system
    public ResponseEntity<ResponseMessageDTO> createContact(ContactInsertDTO contact) {

        try {

            // Validating the fields of the DTO
            var violations = validator.validate(contact);
            if (!violations.isEmpty()) {

                // Collecting all validation error messages
                List<String> errorMessage = violations.stream()
                        .map(violation -> violation.getMessage())
                        .collect(Collectors.toList());

                // Returning a ResponseEntity with BAD_REQUEST status and error messages
                return new ResponseEntity<>(new ResponseMessageDTO(400, "contact.create.notok!",
                        LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
            }

            // Converting DTO to Model
            ContactModel contactModel = contact.convertDtoToModel();

            // Saving the to the repository
            ContactModel contactCreated = contactRepository.save(contactModel);

            // Converting the saved ContactModel back to DTO
            ContactInsertDTO contactDTO = new ContactInsertDTO();
            contactDTO.convertModelToDto(contactCreated);

            // Send email to the admin
            emailService.sendContactAdminEmail(contactCreated.getName(), contactCreated.getEmail(), contactCreated.getMessage());

            // Returning a ResponseEntity with CREATED status and the created DTO
            return new ResponseEntity<>(
                    new ResponseMessageDTO(201, "contact.create.ok!", LocalDateTime.now().toString(), contactDTO),
                    HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseMessageDTO(400, "internal_error!", LocalDateTime.now().toString(), e.toString()),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
}
