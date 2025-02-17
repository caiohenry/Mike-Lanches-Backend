package br.com.mike_lanches.api.apps.user.dto;

// Imports
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// User Model Reference Implementation
import br.com.mike_lanches.api.apps.user.models.UserModel;


@Data
// Create DTO by list - User
public class UserListDTO {

    // ID of User
    private Long id;

    // Name of User
    @NotBlank(message = "Nome do usuário é obrigatório!")
    @Size(max = 255, message = "Nome do usuário deve ter no máximo 255 caracteres!")
    private String user_name;

    // Email of User
    @NotBlank(message = "Email do usuário é obrigatório!")
    @Size(max = 255, message = "Email do usuário deve ter no máximo 255 caracteres!")
    @Email(message = "Email do usuário inválido, por favor insira um email válido!")
    @Column(unique = true)
    private String user_email;

    // Phone of User
    @NotBlank(message = "Telefone do usuário é obrigatório!")
    @Size(max = 255, message = "Telefone do usuário deve ter no máximo 255 caracteres!")
    private String user_phone;

    // Method that converts the Model to the DTO
    public void convertModelToDto(UserModel user) {
        id = user.getId();
        user_name = user.getName();
        user_email = user.getEmail();
        user_phone = user.getPhone();
    }

    // Method that converts the DTO to the Model
    public UserModel convertDtoToModel() {
        UserModel user = new UserModel();
        user.setName(user_name);
        user.setEmail(user_email);
        user.setPhone(user_phone);
        return user;
    }

}