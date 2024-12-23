package uir.ac.ma.inventory.inventoryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import uir.ac.ma.inventory.inventoryapp.model.User;
import uir.ac.ma.inventory.inventoryapp.model.UserDTO;
import uir.ac.ma.inventory.inventoryapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

@Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findByEmailAndPassword(String email, String password) {
        Optional<User> utilisateurOpt = userRepository.findByEmail(email);
        if (utilisateurOpt.isPresent()) {
            User utilisateur = utilisateurOpt.get();
            if (utilisateur.getPassword().equals(password)) {
                return new UserDTO(utilisateur); // Renvoie un DTO à la place de l'entité
            }
        }
        return null; // Retourne null si l'utilisateur n'est pas trouvé ou si le mot de passe est incorrect
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void addUser(UserDTO utilisateurDTO) {
        User utilisateur = new User();
        utilisateur.setName(utilisateurDTO.getName());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setPassword(utilisateurDTO.getPassword());
        utilisateur.setGsm(utilisateurDTO.getGsm());
        utilisateur.setType(utilisateurDTO.getType());
        userRepository.save(utilisateur);
    }


}
