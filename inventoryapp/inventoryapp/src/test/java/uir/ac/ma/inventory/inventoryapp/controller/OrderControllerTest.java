package uir.ac.ma.inventory.inventoryapp.controller;


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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetAllOrders() throws Exception {
        Mockito.when(orderService.getAllOrders())
                .thenReturn(Arrays.asList(new OrdersDTO(1, "Order1"), new OrdersDTO(2, "Order2")));

        mockMvc.perform(get("/api/orders/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Order1"))
                .andExpect(jsonPath("$[1].name").value("Order2"));
    }

    @Test
    public void testAddOrder() throws Exception {
        mockMvc.perform(post("/api/orders/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"NewOrder\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order added successfully."));
    }
}
