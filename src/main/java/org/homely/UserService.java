package org.homely;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<user> allUsers() {
        return userRepository.findAll();
    }
    public String createUser(user request) {
        user newUser = new user(
                request.getUserId(),
                request.getUsername(),
                request.getEmail(),
                request.getFullName(),
                request.getAddress(),
                request.getPhoneNumber(),
                request.getPassword()
        );

        userRepository.save(newUser);

        return "User created successfully"; // Return a success message
    }

    public user findUserByUsername(String username) {
        Optional<user> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null); // Return the user if found, otherwise null
    }
}
