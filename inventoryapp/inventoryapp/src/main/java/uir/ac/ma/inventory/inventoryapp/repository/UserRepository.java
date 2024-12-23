package uir.ac.ma.inventory.inventoryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uir.ac.ma.inventory.inventoryapp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
