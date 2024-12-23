package uir.ac.ma.inventory.inventoryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uir.ac.ma.inventory.inventoryapp.model.OrdersDTO;
import uir.ac.ma.inventory.inventoryapp.model.Orders;
import uir.ac.ma.inventory.inventoryapp.model.Product;
import uir.ac.ma.inventory.inventoryapp.model.User;
import uir.ac.ma.inventory.inventoryapp.repository.OrderRepository;
import uir.ac.ma.inventory.inventoryapp.repository.ProductRepository;
import uir.ac.ma.inventory.inventoryapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository ordersRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // Get all orders
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Add a new order
    @Transactional
    public void addOrder(OrdersDTO ordersDTO) {
        // Fetch the orderer by ID
        Optional<User> ordererOptional = userRepository.findById(ordersDTO.getOrdererId());
        if (!ordererOptional.isPresent()) {
            throw new IllegalArgumentException("Orderer with ID " + ordersDTO.getOrdererId() + " does not exist.");
        }

        // Fetch the supplier by ID
        Optional<User> supplierOptional = userRepository.findById(ordersDTO.getSupplierId());
        if (!supplierOptional.isPresent()) {
            throw new IllegalArgumentException("Supplier with ID " + ordersDTO.getSupplierId() + " does not exist.");
        }

        // Fetch the product by ID
        Optional<Product> productOptional = productRepository.findById(ordersDTO.getProductId());
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Product with ID " + ordersDTO.getProductId() + " does not exist.");
        }

        // Create and set up the Orders entity
        Orders order = new Orders();
        order.setApproved(ordersDTO.isApproved());
        order.setQuantity(ordersDTO.getQuantity());
        order.setDeliveryDate(ordersDTO.getDeliveryDate());
        order.setOrderer(ordererOptional.get());
        order.setSupplier(supplierOptional.get());
        order.setProduct(productOptional.get());

        // Save the order to the database
        ordersRepository.save(order);
    }

    // Delete an order by ID
    @Transactional
    public void deleteOrderById(int id) {
        if (!ordersRepository.existsById(id)) {
            throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
        }
        ordersRepository.deleteById(id);
    }
}
