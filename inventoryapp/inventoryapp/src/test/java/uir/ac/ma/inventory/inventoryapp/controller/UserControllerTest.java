package uir.ac.ma.inventory.inventoryapp.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uir.ac.ma.inventory.inventoryapp.model.UserDTO;
import uir.ac.ma.inventory.inventoryapp.service.UserService;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUsers() throws Exception {
        Mockito.when(userService.getAllUsers())
                .thenReturn(Arrays.asList(new UserDTO(1, "user1@example.com"), new UserDTO(2, "user2@example.com")));

        mockMvc.perform(get("/api/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));
    }

    @Test
    public void testLogin() throws Exception {
        UserDTO mockUser = new UserDTO(1, "user1@example.com", "Admin");
        Mockito.when(userService.findByEmailAndPassword("user1@example.com", "password123"))
                .thenReturn(mockUser);

        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user1@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user1@example.com"))
                .andExpect(jsonPath("$.type").value("Admin"));
    }
}
