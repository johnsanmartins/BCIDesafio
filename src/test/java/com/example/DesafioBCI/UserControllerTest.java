package com.example.DesafioBCI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.DesafioBCI.controller.UserController;
import com.example.DesafioBCI.entity.Telefono;
import com.example.DesafioBCI.entity.Usuario;
import com.example.DesafioBCI.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() throws Exception {
        Usuario user = createUserObject();
        Mockito.when(userService.createUser(Mockito.any(Usuario.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/create")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    private Usuario createUserObject() {
    	Usuario user = new Usuario();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        user.setTelefonos(Collections.singletonList(new Telefono()));
        return user;
    }
}
