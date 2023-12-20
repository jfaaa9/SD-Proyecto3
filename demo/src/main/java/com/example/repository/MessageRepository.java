package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.model.Message;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    // Aquí puedes agregar consultas personalizadas para Message
    // Por ejemplo, encontrar mensajes por remitente o contenido

    @Query("{sender:'?0'}")
    List<Message> findBySender(String sender);

    @Query("{content: {$regex: ?0}}")
    List<Message> findByContentContaining(String content);

    // Otros métodos personalizados según sea necesario
}
