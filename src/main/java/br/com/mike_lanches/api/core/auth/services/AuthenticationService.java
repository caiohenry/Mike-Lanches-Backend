package br.com.mike_lanches.api.core.auth.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mike_lanches.api.apps.user.models.UserModel;
import br.com.mike_lanches.api.apps.user.repositories.UserRepository;
import br.com.mike_lanches.api.core.auth.dto.AuthenticationDTO;
import br.com.mike_lanches.api.core.auth.models.AuthenticationModel;
import br.com.mike_lanches.api.core.auth.models.TokenModel;
import br.com.mike_lanches.api.shared.dto.ResponseMessageDTO;
import jakarta.validation.Validator;

@Service
public class AuthenticationService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    private final Validator validator;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    // Instance validator
    public AuthenticationService(Validator validator, @Lazy AuthenticationManager authenticationManager) {
        this.validator = validator;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    // Method that login the user in the system
    public ResponseEntity<?> authLogin(AuthenticationDTO auth) {

        // Validating the fields of the UserDTO
        var violations = validator.validate(auth);
        if (!violations.isEmpty()) {

            // Collecting all validation error messages
            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            // Returning a ResponseEntity with BAD_REQUEST status and error messages
            return new ResponseEntity<>(new ResponseMessageDTO(400, "user.create.notok", LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Converting AuthenticationDTO to AuthenticationModel
        AuthenticationModel authModel = auth.convertDtoToModel();

        var usernamePassword = new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword());
        var authManager = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) authManager.getPrincipal());
        TokenModel tokenAccess = new TokenModel(token);

        return new ResponseEntity<>(new ResponseMessageDTO(200, "auth.login.ok", LocalDateTime.now().toString(), tokenAccess), HttpStatus.OK);

    } 

        
}

