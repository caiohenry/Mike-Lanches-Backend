package br.com.mike_lanches.api.shared.controllers;


// Imports
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Create controller - Image
@RestController
public class ImageController {

    private final String UPLOAD_DIR = "./uploads/";

    @GetMapping("/image/{imageName}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String imageName) {
        try {
            
            Path imagePath = Paths.get(UPLOAD_DIR).resolve(imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}

