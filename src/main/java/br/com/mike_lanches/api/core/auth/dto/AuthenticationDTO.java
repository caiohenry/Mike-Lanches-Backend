package br.com.mike_lanches.api.core.auth.dto;

import br.com.mike_lanches.api.core.auth.models.AuthenticationModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationDTO {

    // Email of User
    @NotBlank(message = "Email do usuário é obrigatório!")
    private String user_email;

    // Password of User
    @NotBlank(message = "Senha do usuário é obrigatório!")
    private String user_password;

    // Method that converts the DTO to the Model
    public AuthenticationModel convertDtoToModel() {
        AuthenticationModel auth = new AuthenticationModel();
        auth.setEmail(user_email);
        auth.setPassword(user_password);
        return auth;
    }

}
