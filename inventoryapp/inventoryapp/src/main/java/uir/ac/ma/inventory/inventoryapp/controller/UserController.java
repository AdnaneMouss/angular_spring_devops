package uir.ac.ma.inventory.inventoryapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.inventory.inventoryapp.model.User;
import uir.ac.ma.inventory.inventoryapp.model.UserDTO;
import uir.ac.ma.inventory.inventoryapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User utilisateur) {
        UserDTO foundUser = userService.findByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());

        if (foundUser != null) {
            // Return user type with the DTO
            return ResponseEntity.ok().body(foundUser);
        } else {
            // Return 401 if credentials are invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/list")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user.");
        }
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserDTO utilisateurDTO) {
        try {
            userService.addUser(utilisateurDTO);
            return "User added successfully.";
        } catch (Exception e) {
            return"Error adding user.";
        }
    }
}
