package uir.ac.ma.inventory.inventoryapp.model;

import uir.ac.ma.inventory.inventoryapp.model.Product;
import uir.ac.ma.inventory.inventoryapp.model.Category;

public class ProductDTO {

    private int id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int categoryId; // To avoid loading the full category object, we only include the ID.
    private String categoryName; // Optional: to display the category name alongside the product.
private int stock;
    // Default constructor
    public ProductDTO() {}

    // Constructor to map a `Product` entity to this DTO
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.image = product.getImage();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock=product.getStock();
        this.categoryId = product.getCategory() != null ? product.getCategory().getId() : 0;
        this.categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
