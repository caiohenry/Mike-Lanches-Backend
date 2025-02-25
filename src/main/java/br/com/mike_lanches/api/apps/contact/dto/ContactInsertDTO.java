package br.com.mike_lanches.api.apps.contact.dto;

// Imports
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Contact Model Reference Implementation
import br.com.mike_lanches.api.apps.contact.models.ContactModel;


@Data
// Create a new DTO by contact
public class ContactInsertDTO {

    // ID of Contact
    private Long id;

    // Name of Contact
    @NotBlank(message = "Nome do contato é obrigatório!")
    @Size(max = 255, message = "Nome do contato deve ter no máximo 255 caracteres!")
    private String name_contact;

    // Email of Contact
    @NotBlank(message = "Email do contato é obrigatório!")
    @Size(max = 255, message = "Email do contato deve ter no máximo 255 caracteres!")
    @Email(message = "Email do contato inválido, por favor insira um email válido!")
    private String email_contact;

    // Message of Contact
    @NotBlank(message = "Messagem do contato é obrigatório!")
    @Size(max = 500, message = "Messagem do contato deve ter no máximo 500 caracteres!")
    private String message_contact;

    // Method that converts the Model to the DTO
    public void convertModelToDto(ContactModel contact) {
        id = contact.getId();
        name_contact = contact.getName();
        email_contact = contact.getEmail();
        message_contact = contact.getMessage();
    }

    // Method that converts the DTO to the Model
    public ContactModel convertDtoToModel() {
        ContactModel contact = new ContactModel();
        contact.setName(name_contact);
        contact.setEmail(email_contact);
        contact.setMessage(message_contact);
        return contact;
    }

}