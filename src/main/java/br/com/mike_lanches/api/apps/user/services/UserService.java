package br.com.mike_lanches.api.apps.user.services;


// Imports
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.mike_lanches.api.apps.user.dto.UserInsertDTO;
import br.com.mike_lanches.api.apps.user.dto.UserListDTO;
import br.com.mike_lanches.api.apps.user.models.UserModel;
import br.com.mike_lanches.api.apps.user.repositories.UserRepository;
import br.com.mike_lanches.api.shared.dto.PageableListDTO;
import br.com.mike_lanches.api.shared.dto.ResponseMessageDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final Validator validator;

    // Instance validator
    public UserService(Validator validator) {
        this.validator = validator;
    }

    // Method to retrieve paginated and sorted data based on filters and sort order
    public ResponseEntity<?> userGetAll(int page) {

        if (page == -1) {

            // Declare errors message
            List<String> errorMessage = Arrays.asList("Parâmetro 'page' é obrigatório na requisição!");

            // Returning a ResponseEntity with BAD_REQUEST status and error messages
            return new ResponseEntity<>(new ResponseMessageDTO(400, "user.list.notok", LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Fetch paginated data from the repository
        Page<UserModel> userPage = userRepository.findAll(PageRequest.of(page, 10));

        // List dto pages
        List<UserListDTO> user = new ArrayList<>();

        // Extract content from the paginated result
        for (UserModel item : userPage.getContent()) {
            UserListDTO dto = new UserListDTO();
            dto.convertModelToDto(item);
            user.add(dto);
        }

        // Convert Page<UserModel> to PageableListDTO<UserListDTO>
        PageableListDTO<UserListDTO> data = new PageableListDTO<>(user, userPage.getTotalElements());

        // Returning a ResponseEntity with OK status and the list UserListDTO
        return new ResponseEntity<>(new ResponseMessageDTO(200, "user.list.ok", LocalDateTime.now().toString(), data), HttpStatus.OK);
    }

    // Method that registers the user in the system
    public ResponseEntity<?> userCreate(UserInsertDTO user) {

        // Validating the fields of the UserDTO
        var violations = validator.validate(user);
        if (!violations.isEmpty()) {

            // Collecting all validation error messages
            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            // Returning a ResponseEntity with BAD_REQUEST status and error messages
            return new ResponseEntity<>(new ResponseMessageDTO(400, "user.create.notok", LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Converting UserDTO to UserModel
        UserModel userModel = user.convertDtoToModel();

        String passwordEncrypted = new BCryptPasswordEncoder().encode(userModel.getPassword());
        userModel.setPassword(passwordEncrypted);
        userModel.setDateJoined(LocalDate.now());
        userModel.setRemovedDate(LocalDate.now());


        // Genetare random password for the user
        // String password = GeneratorPassword.generate(8);
        // userModel.setPassword(password);

        // Saving the user to the repository
        UserModel userCreated = userRepository.save(userModel);

        // Converting the saved UserModel back to UserDTO
        UserListDTO userDto = new UserListDTO();
        userDto.convertModelToDto(userCreated);

        // Send email to the user
        // emailService.sendEmailText(userCreated.getEmail(), "Usuário Cadastrado com sucesso!",
        //         "Sucesso ao criar seu usuário no sistema!");

        // Returning a ResponseEntity with CREATED status and the created UserDTO
        return new ResponseEntity<>(new ResponseMessageDTO(201, "user.create.ok", LocalDateTime.now().toString(), userDto), HttpStatus.CREATED);
    }

    public void saveUsers(List<UserInsertDTO> users) {

        System.out.println(users);

        for (UserInsertDTO userInsertDTO : users) {
            UserModel userModel = userInsertDTO.convertDtoToModel();
            if(userRepository.findByEmail(userModel.getEmail()) == null) {
                userRepository.save(userModel);
            }  
        }
    }
    
}
