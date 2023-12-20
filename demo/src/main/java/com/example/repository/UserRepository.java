package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    
    // Método para encontrar un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);
}
