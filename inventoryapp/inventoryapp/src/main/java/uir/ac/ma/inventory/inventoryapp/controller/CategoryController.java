package uir.ac.ma.inventory.inventoryapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.inventory.inventoryapp.model.Category;
import uir.ac.ma.inventory.inventoryapp.model.CategoryDTO;
import uir.ac.ma.inventory.inventoryapp.model.UserDTO;
import uir.ac.ma.inventory.inventoryapp.service.CategoryService;
import uir.ac.ma.inventory.inventoryapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<CategoryDTO> getCategories() {
        System.out.println(categoryService.getAllCategories());
        return categoryService.getAllCategories().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        try {
            categoryService.deletecategoryById(id);
            return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user.");
        }
    }

    @PostMapping("/add")
    public String addCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.addcategory(categoryDTO);
            return "User added successfully.";
        } catch (Exception e) {
            return"Error adding user.";
        }
    }
}
