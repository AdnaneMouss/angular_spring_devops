package uir.ac.ma.inventory.inventoryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uir.ac.ma.inventory.inventoryapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
