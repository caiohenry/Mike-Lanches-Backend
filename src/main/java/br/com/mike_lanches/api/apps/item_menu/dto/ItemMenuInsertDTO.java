package br.com.mike_lanches.api.apps.item_menu.dto;

// Imports
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Item Menu Model Reference Implementation
import br.com.mike_lanches.api.apps.item_menu.models.ItemMenuModel;


@Data
// Create a new DTO by item menu
public class ItemMenuInsertDTO {

    // ID of Item Menu
    private Long id;

    // Name of Item Menu
    @NotBlank(message = "Nome do item é obrigatório!")
    @Size(max = 255, message = "Nome do item deve ter no máximo 255 caracteres!")
    private String name_item_menu;

    // Description of Item Menu
    @NotBlank(message = "Descrição do item é obrigatório!")
    @Size(max = 500, message = "Descrição do item deve ter no máximo 500 caracteres!")
    private String description_item_menu;

    // Image of Item Menu
    private String image_item_menu;

    // Method that converts the Model to the DTO
    public void convertModelToDto(ItemMenuModel itemMenu) {
        id = itemMenu.getId();
        name_item_menu = itemMenu.getName();
        description_item_menu = itemMenu.getDescription();
        image_item_menu = itemMenu.getImage();
    }

    // Method that converts the DTO to the Model
    public ItemMenuModel convertDtoToModel() {
        ItemMenuModel itemMenu = new ItemMenuModel();
        itemMenu.setName(name_item_menu);
        itemMenu.setDescription(description_item_menu);
        itemMenu.setImage(image_item_menu);
        return itemMenu;
    }

}