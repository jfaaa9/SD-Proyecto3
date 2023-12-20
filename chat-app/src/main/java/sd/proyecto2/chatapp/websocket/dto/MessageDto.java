package sd.proyecto2.chatapp.websocket.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Constructor, getters y setters
    public MessageDto(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now(); // O asignar en el servidor
    }

    // Getters y setters
    // ...
}

