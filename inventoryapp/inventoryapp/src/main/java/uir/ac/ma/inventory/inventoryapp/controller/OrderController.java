package uir.ac.ma.inventory.inventoryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.inventory.inventoryapp.model.OrdersDTO;
import uir.ac.ma.inventory.inventoryapp.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService ordersService;

    public OrderController(OrderService ordersService) {
        this.ordersService = ordersService;
    }

    // Get all orders
    @GetMapping("/list")
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders().stream()
                .map(OrdersDTO::new)
                .collect(Collectors.toList());
    }

    // Add a new order
    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody OrdersDTO ordersDTO) {
        try {
            ordersService.addOrder(ordersDTO);
            return ResponseEntity.ok("Order added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding order.");
        }
    }

    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        try {
            ordersService.deleteOrderById(id);
            return ResponseEntity.ok("Order with ID " + id + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting order.");
        }
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveOrder(@PathVariable int id) {
        try {
            ordersService.approveOrder(id); // Call the service method
            return ResponseEntity.ok("Order with ID " + id + " approved successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error approving the order.");
        }
    }
}
