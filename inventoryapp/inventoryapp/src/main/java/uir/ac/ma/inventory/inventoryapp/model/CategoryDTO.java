package uir.ac.ma.inventory.inventoryapp.model;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {
    private int id;
    private String name;
    private String image;
    private List<Integer> productIds; // IDs of associated products

    // Default constructor (needed for deserialization)
    public CategoryDTO() {
    }

    // Constructor to map a `Category` entity to this DTO
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.image = category.getImage();
        // Map associated products to their IDs
        this.productIds = category.getProducts() != null
                ? category.getProducts().stream().map(Product::getId).collect(Collectors.toList())
                : null;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
