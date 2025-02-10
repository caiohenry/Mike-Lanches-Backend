package br.com.mikelanches.api.apps.item_menu.controllers;


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
import br.com.mikelanches.api.apps.item_menu.models.ItemMenuModel;
import br.com.mikelanches.api.apps.item_menu.services.ItemMenuService;
import br.com.mikelanches.api.shared.dto.PageableListDTO;
import br.com.mikelanches.api.apps.item_menu.dto.ItemMenuAllDTO;
import br.com.mikelanches.api.apps.item_menu.dto.ItemMenuListDTO;


// Create a new controller by item menu
@RestController
@RequestMapping(value = "/item-menu")
public class ItemMenuController {

    @Autowired
    private ItemMenuService itemMenuService;

    // GET - URL to get all itens menu of the system
    @GetMapping("/")
    private PageableListDTO<ItemMenuListDTO> getAllItensMenu(@RequestParam int page, @RequestParam int pageOrder) {
        return itemMenuService.getAllItensMenu(page, pageOrder);
    }

    // POST - URL to insert data item menu of the system
    @PostMapping("/create/")
    private ResponseEntity<?> createItemMenu(@ModelAttribute ItemMenuAllDTO itemMenu,  @RequestParam(value = "file", required = false) MultipartFile file) {
        return itemMenuService.createItemMenu(itemMenu, file);
    }
    
}





