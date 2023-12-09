package org.homely.Controllers;
import org.homely.Property;
import org.homely.UserRepository;
import org.homely.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.homely.PropertyService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class PropertyController {
    private PropertyService propertyService;
    private UserRepository userRepository;

    @Autowired
    public PropertyController(PropertyService propertyService, UserRepository userRepository) {
        this.propertyService = propertyService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{username}/create_property")
    public ResponseEntity<String> createProperty(@ModelAttribute Property request,
                                                 @PathVariable("username") String username,
                                                 @RequestParam("photoUpload") List<MultipartFile> photos) throws IOException {

        // Fetch user by username
        Optional<user> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            user user = optionalUser.get();

            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String filename = photo.getOriginalFilename();
                    String directory = "C:\\Users\\USER\\PycharmProjects\\Homely-front\\public\\imgs";
                    Path filePath = Paths.get(directory, filename);
                    Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    request.addPhoto(filename);
                    request.setUserId(user.getUserId());
                }
            }

            String savedProperty = propertyService.createProperty(request);

            if (savedProperty != null) {
                // Add property ID to user's property list
                user.getProperties().add(savedProperty);
                userRepository.save(user); // Save user with updated property list

                return ResponseEntity.ok("Property created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save property");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
