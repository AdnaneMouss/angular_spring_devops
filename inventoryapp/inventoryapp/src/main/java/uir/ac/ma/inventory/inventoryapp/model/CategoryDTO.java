package uir.ac.ma.inventory.inventoryapp.model;

public class CategoryDTO {

    private int id;
    private String name;

    public CategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(){}

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
}
