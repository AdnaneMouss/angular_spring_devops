package uir.ac.ma.inventory.inventoryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uir.ac.ma.inventory.inventoryapp.model.Category;
import uir.ac.ma.inventory.inventoryapp.model.CategoryDTO;
import uir.ac.ma.inventory.inventoryapp.repository.CategoryRepository;


import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void deletecategoryById(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void addcategory(CategoryDTO categoryDTO) {
        Category category = new Category(1, "Electronics");
        category.setName(categoryDTO.getName());
        category.setImage(categoryDTO.getImage());
        categoryRepository.save(category);
    }

}
