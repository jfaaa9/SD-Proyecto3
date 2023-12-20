package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Message {

    @Id
    private String id;
    private String sender; // Quién envía el mensaje
    private String content; // Contenido del mensaje
    private LocalDateTime timestamp; // Hora de envío del mensaje

    @Override
    public String toString() {
        return "Message{" +
               "id='" + id + '\'' +
               ", sender='" + sender + '\'' +
               ", content='" + content + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}
