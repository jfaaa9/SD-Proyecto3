package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.model.Message;
import com.example.service.MessageService;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // POST: Crear un nuevo mensaje
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    // GET: Obtener todos los mensajes
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // GET: Obtener un mensaje por ID
    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable String id) {
        return messageService.getMessageById(id).orElse(null);
    }

    // PUT: Actualizar un mensaje
    @PutMapping("/messages/{id}")
    public Message updateMessage(@PathVariable String id, @RequestBody Message message) {
        return messageService.updateMessage(id, message).orElse(null);
    }

    // DELETE: Eliminar un mensaje
    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
    }

    @GetMapping("/get-test")
    public String getTestMessage() {
        return "Este es un mensaje de prueba.";
    }

    // Agrega aquí otros métodos según sea necesario
}

