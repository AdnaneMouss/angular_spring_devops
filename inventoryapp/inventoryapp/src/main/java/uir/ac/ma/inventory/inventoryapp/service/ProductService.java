package uir.ac.ma.inventory.inventoryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uir.ac.ma.inventory.inventoryapp.model.*;
import uir.ac.ma.inventory.inventoryapp.repository.CategoryRepository;
import uir.ac.ma.inventory.inventoryapp.repository.ProductRepository;
import uir.ac.ma.inventory.inventoryapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProductById(int id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void addProduct(ProductDTO productDTO) {
        // Fetch the category by ID
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());

        if (!categoryOptional.isPresent()) {
            throw new IllegalArgumentException("Category with ID " + productDTO.getCategoryId() + " does not exist.");
        }

        // Create and set up the Product entity
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setCategory(categoryOptional.get()); // Set the associated category
product.setStock(productDTO.getStock());
        // Save the product to the database
        productRepository.save(product);
    }

}
