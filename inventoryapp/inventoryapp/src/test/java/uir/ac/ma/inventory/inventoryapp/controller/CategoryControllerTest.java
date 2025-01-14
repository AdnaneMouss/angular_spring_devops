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
import uir.ac.ma.inventory.inventoryapp.model.CategoryDTO;
import uir.ac.ma.inventory.inventoryapp.service.CategoryService;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);
        categoryDTO.setName("Test Category");
    }

    @Test
    void testGetCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(categoryDTO.toCategory()));

        mockMvc.perform(get("/api/category/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Category"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deletecategoryById(1);

        mockMvc.perform(delete("/api/category/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User with ID 1 deleted successfully."));
    }

    @Test
    void testAddCategory() throws Exception {
        doNothing().when(categoryService).addcategory(Mockito.any(CategoryDTO.class));

        mockMvc.perform(post("/api/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully."));
    }
}
