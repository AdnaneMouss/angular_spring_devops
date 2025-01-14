package uir.ac.ma.inventory.inventoryapp.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uir.ac.ma.inventory.inventoryapp.model.ProductDTO;
import uir.ac.ma.inventory.inventoryapp.service.ProductService;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetCategories() throws Exception {
        Mockito.when(productService.getAllProducts())
                .thenReturn(Arrays.asList(new ProductDTO(1, "Laptop"), new ProductDTO(2, "Phone")));

        mockMvc.perform(get("/api/product/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].name").value("Phone"));
    }

    @Test
    public void testAddProduct() throws Exception {
        mockMvc.perform(post("/api/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Tablet\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully."));
    }
}
