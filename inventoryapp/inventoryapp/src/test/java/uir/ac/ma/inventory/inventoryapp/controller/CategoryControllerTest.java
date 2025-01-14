package uir.ac.ma.inventory.inventoryapp.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uir.ac.ma.inventory.inventoryapp.model.Category;
import uir.ac.ma.inventory.inventoryapp.model.CategoryDTO;
import uir.ac.ma.inventory.inventoryapp.service.CategoryService;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void testGetCategories() throws Exception {
        Mockito.when(categoryService.getAllCategories())
                .thenReturn(Arrays.asList(new Category(1, "Electronics"), new Category(2, "Clothing")));

        mockMvc.perform(get("/api/category/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].name").value("Clothing"));
    }

    @Test
    public void testAddCategory() throws Exception {
        mockMvc.perform(post("/api/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Books\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully."));
    }
}
