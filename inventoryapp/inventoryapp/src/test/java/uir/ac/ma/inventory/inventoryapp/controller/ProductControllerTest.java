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
import uir.ac.ma.inventory.inventoryapp.model.ProductDTO;
import uir.ac.ma.inventory.inventoryapp.service.ProductService;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("Test Product");
        productDTO.setPrice(100.0);
    }

    @Test
    void testGetCategories() throws Exception {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(productDTO.toProduct()));

        mockMvc.perform(get("/api/product/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Product"))
                .andExpect(jsonPath("$[0].price").value(100.0));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProductById(1);

        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product with ID 1 deleted successfully."));
    }

    @Test
    void testAddProduct() throws Exception {
        doNothing().when(productService).addProduct(Mockito.any(ProductDTO.class));

        mockMvc.perform(post("/api/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully."));
    }
}
