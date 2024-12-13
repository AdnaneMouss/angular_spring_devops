package uir.ac.ma.inventory.inventoryapp.model;

public class UserDTO {

    private int id;
    private String name;
    private String email;
    private UserType type;

    public UserDTO(int id, String name, String email, UserType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }
public UserDTO(){}
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
