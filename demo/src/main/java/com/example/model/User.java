package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;
    private String username;
    private String password; // Considera almacenar las contraseñas de forma segura
    private String role;     // Ejemplo: "admin", "user"
    private LocalDateTime lastLogin;

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", role='" + role + '\'' +
               ", lastLogin=" + lastLogin +
               '}';
    }
}
