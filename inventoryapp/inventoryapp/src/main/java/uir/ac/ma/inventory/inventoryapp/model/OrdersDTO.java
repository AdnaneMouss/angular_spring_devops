package uir.ac.ma.inventory.inventoryapp.model;

import uir.ac.ma.inventory.inventoryapp.model.Product;
import uir.ac.ma.inventory.inventoryapp.model.User;

import java.util.Date;

public class OrdersDTO {
    private int id;
    private boolean approved;
    private int quantity;
    private Date deliveryDate;
    private int ordererId; // ID of the orderer
    private String ordererName; // Name of the orderer
    private int supplierId; // ID of the supplier
    private String supplierName; // Name of the supplier
    private int productId; // ID of the product
    private String productName; // Name of the product

    // Default constructor
    public OrdersDTO() {}

    // Constructor to map `Orders` entity to `OrdersDTO`
    public OrdersDTO(Orders orders) {
        this.id = orders.getId();
        this.approved = orders.isApproved();
        this.quantity = orders.getQuantity();
        this.deliveryDate = orders.getDeliveryDate();
        this.ordererId = orders.getOrderer().getId();
        this.ordererName = orders.getOrderer().getName();
        this.supplierId = orders.getSupplier().getId();
        this.supplierName = orders.getSupplier().getName();
        this.productId = orders.getProduct().getId();
        this.productName = orders.getProduct().getName();
    }

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

    public int getOrdererId() {
        return ordererId;
    }

    public void setOrdererId(int ordererId) {
        this.ordererId = ordererId;
    }

    public String getOrdererName() {
        return ordererName;
    }

    public void setOrdererName(String ordererName) {
        this.ordererName = ordererName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
