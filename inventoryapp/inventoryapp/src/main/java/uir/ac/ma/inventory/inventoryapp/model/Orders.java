package uir.ac.ma.inventory.inventoryapp.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean approved;
    private int quantity;
    private Date deliveryDate;
    @ManyToOne
    @JoinColumn(name = "orderer_id", nullable = false )
    private User orderer;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false )
    private User supplier;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Orders(int id, boolean approved, int quantity, Date deliveryDate, User orderer, User supplier, Product product) {
        this.id = id;
        this.approved = approved;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.orderer = orderer;
        this.supplier = supplier;
        this.product = product;
    }

    public Orders() {}

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public User getOrderer() {
        return orderer;
    }

    public void setOrderer(User orderer) {
        this.orderer = orderer;
    }

    public User getSupplier() {
        return supplier;
    }

    public void setSupplier(User supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
