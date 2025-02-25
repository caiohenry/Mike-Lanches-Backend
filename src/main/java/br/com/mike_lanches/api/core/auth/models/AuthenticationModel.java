package br.com.mike_lanches.api.core.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthenticationModel {

    
    @JsonProperty("user_email")
    private String email;


    @JsonProperty("user_password")
    private String password;
}
