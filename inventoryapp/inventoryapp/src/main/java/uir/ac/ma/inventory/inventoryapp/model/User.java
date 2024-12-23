package uir.ac.ma.inventory.inventoryapp.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;
    private String gsm;
    private String image;
    private String type;

    @OneToMany(mappedBy = "orderer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Orders> order;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Orders> supplierorder;


    public User(int id, String name, String email, String password, String type,String image, List<Orders> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.order = orders;
        this.image=image;
    }
    public User(){
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Orders> getOrders() {
        return order;
    }

    public void setOrders(List<Orders> orders) {
        this.order = orders;
    }

    public String getGsm() {
        return gsm;
    }
    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

