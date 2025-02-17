package br.com.mike_lanches.api.apps.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mike_lanches.api.apps.user.dto.UserInsertDTO;
import br.com.mike_lanches.api.apps.user.services.UserService;

// Create controller - User
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    // GET - URL to get all users of the system
    @GetMapping("/")
    private ResponseEntity<?> userGetAll(@RequestParam(required = false) Integer page) {

        // Opcional parameters
        if (page == null) {
            page = -1;
        }
    
        return userService.userGetAll(page);
    }

    // POST - URL to insert user data of the system
    @PostMapping("/create/")
    private ResponseEntity<?> userCreate(@RequestBody UserInsertDTO user) {
        return userService.userCreate(user);
    }
    
}
