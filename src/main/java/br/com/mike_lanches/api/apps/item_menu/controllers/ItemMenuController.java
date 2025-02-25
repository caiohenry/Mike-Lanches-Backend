package br.com.mike_lanches.api.apps.item_menu.controllers;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// Item Menu Reference Implementation
import br.com.mike_lanches.api.apps.item_menu.services.ItemMenuService;
import br.com.mike_lanches.api.shared.dto.ResponseMessageDTO;
import br.com.mike_lanches.api.apps.item_menu.dto.ItemMenuInsertDTO;


// Create a new controller by item menu
@RestController
@RequestMapping(value = "/item-menu")
public class ItemMenuController {

    @Autowired
    private ItemMenuService itemMenuService;

    // GET - URL to get all items menu of the system
    @GetMapping("/")
    private ResponseEntity<ResponseMessageDTO> userGetAll(@RequestParam(required = false) Integer page) {

        // Opcional parameters
        if (page == null) {
            page = -1;
        }
    
        return itemMenuService.itemMenuGetAll(page);
    }

    // POST - URL to insert data item menu of the system
    @PostMapping("/create/")
    private ResponseEntity<ResponseMessageDTO> createItemMenu(@ModelAttribute ItemMenuInsertDTO itemMenu,  @RequestParam(required = false) MultipartFile file) {
        return itemMenuService.createItemMenu(itemMenu, file);
    }
    
}





