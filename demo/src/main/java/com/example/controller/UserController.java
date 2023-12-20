package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LoginCredentials;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping("/users/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username).orElse(null);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginCredentials credentials) {
        if (userService.validateUser(credentials)) {
            // Recupera los datos del usuario por su nombre de usuario
            User user = userService.getUserByUsername(credentials.getUsername()).orElse(null);
            if (user != null) {
                // Elimina la contraseña antes de enviarla como respuesta
                user.setPassword(null);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejar el caso de error aquí
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    // Más endpoints según sea necesario
}
