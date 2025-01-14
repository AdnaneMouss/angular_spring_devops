package uir.ac.ma.inventory.inventoryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uir.ac.ma.inventory.inventoryapp.model.OrdersDTO;
import uir.ac.ma.inventory.inventoryapp.service.OrderService;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private OrdersDTO ordersDTO;

    @BeforeEach
    void setUp() {
        ordersDTO = new OrdersDTO();
        ordersDTO.setId(1);
        ordersDTO.setStatus("Pending");
        ordersDTO.setDescription("Test Order");
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(ordersDTO.toOrder()));

        mockMvc.perform(get("/api/orders/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].status").value("Pending"))
                .andExpect(jsonPath("$[0].description").value("Test Order"));
    }

    @Test
    void testAddOrder() throws Exception {
        doNothing().when(orderService).addOrder(Mockito.any(OrdersDTO.class));

        mockMvc.perform(post("/api/orders/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ordersDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Order added successfully."));
    }

    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrderById(1);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order with ID 1 deleted successfully."));
    }

    @Test
    void testApproveOrder() throws Exception {
        doNothing().when(orderService).approveOrder(1);

        mockMvc.perform(put("/api/orders/approve/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order with ID 1 approved successfully."));
    }
}
