package org.homely;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AppController {

    private final PropertyService propertyService;
    private final UserService userService;

    @Autowired
    public AppController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties(){
        List<Property> properties = propertyService.allProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<user>> getAllUsers(){
        List<user> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody user request) {
        String responseMessage = userService.createUser(request);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/log_in")
    public ResponseEntity<String> logIn(@RequestBody user request) {
        String username = request.getUsername();
        String password = request.getPassword();
        System.out.println("Received login attempt for username: " + username);
        user foundUser = userService.findUserByUsername(username);

        if (foundUser != null) {
            System.out.println("Found user: " + foundUser.getUsername());
            if (foundUser.getPassword().equals(password)) {
                return ResponseEntity.ok("Login successful");
            }
        }

        System.out.println("Login failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @PostMapping("/search")
    public ResponseEntity<List<Property>> searchPropertiesByDateRange(@RequestBody Property dateRangeRequest) {
        LocalDate startDate = dateRangeRequest.getStartDate();
        LocalDate endDate = dateRangeRequest.getEndDate();
        List<Property> properties = propertyService.getPropertiesByDateRange(startDate, endDate);

        return ResponseEntity.ok(properties);
    }
}

