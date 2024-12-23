package uir.ac.ma.inventory.inventoryapp.model;

import uir.ac.ma.inventory.inventoryapp.model.User;
import uir.ac.ma.inventory.inventoryapp.model.Orders;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String gsm;
    private String type;
    private String password; // Include if needed; remove for security
    private List<Integer> orderIds; // IDs of associated orders
    private String image;
    // Default constructor (needed for deserialization)
    public UserDTO() {
    }

    // Constructor to map a `User` entity to this DTO
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.gsm = user.getGsm();
        this.type = user.getType(); // Convert enum to string
        this.password = user.getPassword(); // Include if required
        this.image=user.getImage();
        // Map associated orders to their IDs
        this.orderIds = user.getOrders() != null
                ? user.getOrders().stream().map(Orders::getId).collect(Collectors.toList())
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
         this.image = image;
    }
}
