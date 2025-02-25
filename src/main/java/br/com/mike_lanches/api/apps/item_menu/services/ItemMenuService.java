package br.com.mike_lanches.api.apps.item_menu.services;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Validator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Path;

// Item Menu Reference Implementation
import br.com.mike_lanches.api.apps.item_menu.repositories.ItemMenuRepository;
import br.com.mike_lanches.api.shared.dto.PageableListDTO;
import br.com.mike_lanches.api.shared.dto.ResponseMessageDTO;
import br.com.mike_lanches.api.apps.item_menu.dto.ItemMenuInsertDTO;
import br.com.mike_lanches.api.apps.item_menu.dto.ItemMenuListDTO;
import br.com.mike_lanches.api.apps.item_menu.models.ItemMenuModel;


// Create a new service by item menu
@Service
public class ItemMenuService {

    // Upload dir
    private final String UPLOAD_DIR = "./uploads/";

    @Autowired
    private ItemMenuRepository itemMenuRepository;
    private final Validator validator;

    // Instance validator
    public ItemMenuService(Validator validator) {
        this.validator = validator;
    }

    // Method to retrieve paginated and sorted data based on filters and sort order
    public ResponseEntity<ResponseMessageDTO> itemMenuGetAll(int page) {

        if (page == -1) {

            // Declare errors message
            List<String> errorMessage = Arrays.asList("Parâmetro 'page' é obrigatório na requisição!");

            // Returning a ResponseEntity with BAD_REQUEST status and error messages
            return new ResponseEntity<>(new ResponseMessageDTO(400, "item_menu.list.notok", LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Fetch paginated data from the repository
        Page<ItemMenuModel> itemMenuPage = itemMenuRepository.findAll(PageRequest.of(page, 10));

        // List dto pages
        List<ItemMenuListDTO> itemMenu = new ArrayList<>();

        // Extract content from the paginated result
        for (ItemMenuModel item : itemMenuPage.getContent()) {
            ItemMenuListDTO dto = new ItemMenuListDTO();
            dto.convertModelToDto(item);
            itemMenu.add(dto);
        }

        // Convert Page<ItemMenuModel> to PageableListDTO<ItemMenuListDTO>
        PageableListDTO<ItemMenuListDTO> data = new PageableListDTO<>(itemMenu, itemMenuPage.getTotalElements());

        // Returning a ResponseEntity with OK status and the list ItemMenuListDTO
        return new ResponseEntity<>(new ResponseMessageDTO(200, "item_menu.list.ok", LocalDateTime.now().toString(), data), HttpStatus.OK);
    }


    

    // Method that registers the item menu in the system
    public ResponseEntity<ResponseMessageDTO> createItemMenu(ItemMenuInsertDTO itemMenu, MultipartFile file) {

        try {

            if (file == null) {
                List<String> errorMessage = Arrays.asList("Imagem do item é obrigatório!");
                return new ResponseEntity<>(new ResponseMessageDTO(400, "item_menu.create.notok!",
                        LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
            }

            // Validating the fields of the DTO
            var violations = validator.validate(itemMenu);
            if (!violations.isEmpty()) {

                // Collecting all validation error messages
                List<String> errorMessage = violations.stream()
                        .map(violation -> violation.getMessage())
                        .collect(Collectors.toList());

                // Returning a ResponseEntity with BAD_REQUEST status and error messages
                return new ResponseEntity<>(new ResponseMessageDTO(400, "item_menu.create.notok!",
                        LocalDateTime.now().toString(), errorMessage), HttpStatus.BAD_REQUEST);
            }

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String imagePath = UPLOAD_DIR + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(imagePath));
            

            
            // Converting DTO to Model
            ItemMenuModel itemMenuModel = itemMenu.convertDtoToModel();
            itemMenuModel.setImage(imagePath);

            // Saving the to the repository
            ItemMenuModel itemMenuCreated = itemMenuRepository.save(itemMenuModel);

            // Converting the saved ItemMenuModel back to DTO
            ItemMenuInsertDTO itemMenuDTO = new ItemMenuInsertDTO();
            itemMenuDTO.convertModelToDto(itemMenuCreated);

            // Returning a ResponseEntity with CREATED status and the created DTO
            return new ResponseEntity<>(
                    new ResponseMessageDTO(201, "item_menu.create.ok!", LocalDateTime.now().toString(), itemMenuDTO),
                    HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseMessageDTO(400, "internal_error!", LocalDateTime.now().toString(), e.toString()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
