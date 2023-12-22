package sd.proyecto2.chatapp.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

import sd.proyecto2.chatapp.websocket.dto.MessageDto;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private RestTemplate restTemplate = new RestTemplate();
    //private final String apiUrl = "http://localhost:8081/messages"; // URL de tu API REST
    //private final String apiUrl = "http://api:8081/messages";
    private final String apiUrl = "http://34.176.210.55:8081/messages";

    /* 
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Aquí manejas el mensaje recibido
        String clientMessage = message.getPayload();
        System.out.println("Mensaje recibido: " + clientMessage);

        // Enviar una respuesta
        TextMessage replyMessage = new TextMessage("Mensaje recibido: " + clientMessage);
        session.sendMessage(replyMessage);
    }
    */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        // Deserializar la cadena JSON a un array
        ObjectMapper mapper = new ObjectMapper();
        List<String> messageData = mapper.readValue(message.getPayload(), new TypeReference<List<String>>(){});

        // Suponiendo que el array tiene dos elementos: [userId, messageText]
        String userId = messageData.get(0);
        String messageText = messageData.get(1);
        String userName = messageData.get(2);

        // Crear un nuevo TextMessage con solo el texto del mensaje
        String formattedMessage = userName + ": " + messageText;
        TextMessage textMessageToSend = new TextMessage(formattedMessage);

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(textMessageToSend);
            }
            // Imprimir el mensaje retransmitido en la consola del servidor
            System.out.println("Mensaje retransmitido a " + webSocketSession.getId() + ": " + message.getPayload());
        }

        // Guardar el mensaje en la base de datos solo una vez
        //MessageDto messageDto = new MessageDto(session.getId(), message.getPayload());
        MessageDto messageDto = new MessageDto(userId, messageText);
        restTemplate.postForObject(apiUrl, messageDto, MessageDto.class);

        // Imprimir el mensaje recibido en la consola del servidor
        //System.out.println("Mensaje de " + session.getId() + ": " + message.getPayload());
        System.out.println("Mensaje de " + userId + ": " + messageText);
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Obtener el ID único de la sesión
        String clientId = session.getId();

        sessions.add(session);

        // Log de conexion
        System.out.println("Cliente conectado: " + session.getId());

        // Enviar el ID al cliente
        session.sendMessage(new TextMessage("Tu ID de cliente es: " + clientId));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        // Aquí puedes manejar cuando un cliente se desconecta
        System.out.println("Cliente desconectado: " + session.getId());
}
}
