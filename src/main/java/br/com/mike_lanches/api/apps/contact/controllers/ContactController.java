package br.com.mike_lanches.api.apps.contact.controllers;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.mike_lanches.api.shared.dto.ResponseMessageDTO;

// Contact Reference Implementation
import br.com.mike_lanches.api.apps.contact.dto.ContactInsertDTO;
import br.com.mike_lanches.api.apps.contact.services.ContactService;


// Create a new controller by item menu
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // GET - URL to get all items menu of the system
    // @GetMapping("/")
    // private ResponseEntity<ResponseMessageDTO> contactGetAll(@RequestParam(required = false) Integer page) {

    //     // Opcional parameters
    //     if (page == null) {
    //         page = -1;
    //     }
    
    //     return contactService.(page);
    // }

    // POST - URL to insert data contact of the system
    @PostMapping("/create/")
    private ResponseEntity<ResponseMessageDTO> createContact(@RequestBody ContactInsertDTO contact) {
        return contactService.createContact(contact);
    }
    
}
