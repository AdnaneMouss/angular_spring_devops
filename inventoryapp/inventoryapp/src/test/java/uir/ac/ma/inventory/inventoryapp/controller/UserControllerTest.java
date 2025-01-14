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
import uir.ac.ma.inventory.inventoryapp.model.User;
import uir.ac.ma.inventory.inventoryapp.model.UserDTO;
import uir.ac.ma.inventory.inventoryapp.service.UserService;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setEmail("test@example.com");
        userDTO.setType("admin");
    }

    @Test
    void testLoginSuccess() throws Exception {
        when(userService.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(userDTO);

        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.type").value("admin"));
    }

    @Test
    void testLoginFailure() throws Exception {
        when(userService.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(null);

        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    void testGetUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userDTO.toUser()));

        mockMvc.perform(get("/api/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@example.com"))
                .andExpect(jsonPath("$[0].type").value("admin"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUserById(1);

        mockMvc.perform(delete("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User with ID 1 deleted successfully."));
    }

    @Test
    void testAddUser() throws Exception {
        doNothing().when(userService).addUser(Mockito.any(UserDTO.class));

        mockMvc.perform(post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully."));
    }
}
