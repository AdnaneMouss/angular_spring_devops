package uir.ac.ma.inventory.inventoryapp.model;

public class OrderDTO {

    private int id;
    private String description;
    private boolean approved;
    private int userId;
    private int productId;

    public OrderDTO(int id, String description, boolean approved, int userId, int productId) {
        this.id = id;
        this.description = description;
        this.approved = approved;
        this.userId = userId;
        this.productId = productId;
    }

    public OrderDTO(){}

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
