package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.model.Message;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Método para guardar un mensaje
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Método para obtener todos los mensajes
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Método para obtener un mensaje por su ID
    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    // Método para actualizar un mensaje
    public Optional<Message> updateMessage(String id, Message updatedMessage) {
        Optional<Message> existingMessage = messageRepository.findById(id);
        if (existingMessage.isPresent()) {
            // Actualizar el mensaje existente con los datos de updatedMessage
            Message message = existingMessage.get();
            message.setContent(updatedMessage.getContent());
            // Agregar más campos a actualizar según tus necesidades
            // Guardar el mensaje actualizado en la base de datos
            messageRepository.save(message);
        }
        return existingMessage;
    }

    // Método para eliminar un mensaje por su ID
    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }

    // Puedes agregar más métodos según tus necesidades
    // Por ejemplo, métodos para buscar mensajes por criterios específicos
}
