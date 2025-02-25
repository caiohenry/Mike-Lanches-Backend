package br.com.mike_lanches.api.core.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TokenModel {


    public TokenModel(String token) {
        this.token = token;
    }


    @JsonProperty("token_access")
    private String token;
   
}
