package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.UserRepository;
import com.example.dto.LoginCredentials;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean validateUser(LoginCredentials credentials) {
    Optional<User> user = userRepository.findByUsername(credentials.getUsername());
    if (user.isPresent()) {
        return user.get().getPassword().equals(credentials.getPassword());
    }
    return false;
    }


    // Métodos adicionales según sea necesario
}
