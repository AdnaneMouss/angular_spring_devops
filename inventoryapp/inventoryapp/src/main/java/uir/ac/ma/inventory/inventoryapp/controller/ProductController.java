package uir.ac.ma.inventory.inventoryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.inventory.inventoryapp.model.ProductDTO;
import uir.ac.ma.inventory.inventoryapp.service.CategoryService;
import uir.ac.ma.inventory.inventoryapp.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public List<ProductDTO> getCategories() {
        return productService.getAllProducts().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteproduct(@PathVariable int id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok("Product with ID " + id + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user.");
        }
    }

    @PostMapping("/add")
    public String addproduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.addProduct(productDTO);
            return "User added successfully.";
        } catch (Exception e) {
            return"Error adding user.";
        }
    }
}
