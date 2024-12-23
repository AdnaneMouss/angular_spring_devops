package uir.ac.ma.inventory.inventoryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uir.ac.ma.inventory.inventoryapp.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
