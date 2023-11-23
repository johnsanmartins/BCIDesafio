package com.example.DesafioBCI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.DesafioBCI.entity.Telefono;
import com.example.DesafioBCI.entity.Usuario;
import com.example.DesafioBCI.exception.UserAlreadyExistsException;
import com.example.DesafioBCI.repository.UserRepository;
import com.example.DesafioBCI.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser_Success() {
        Usuario user = createUserObject();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(Usuario.class))).thenReturn(user);

        Usuario createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getName(), createdUser.getName());
        // Asegúrate de verificar otros campos según sea necesario
    }

    @Test
    public void testCreateUser_UserAlreadyExistsException() {
    	Usuario user = createUserObject();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(user));
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
