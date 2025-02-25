package br.com.mike_lanches.api.core.auth.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.mike_lanches.api.core.auth.dto.AuthenticationDTO;
import br.com.mike_lanches.api.core.auth.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token-auth/")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO auth) {
        return authenticationService.authLogin(auth);
    }

}
