package sd.proyecto2.chatapp.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import sd.proyecto2.chatapp.websocket.dto.MessageDto;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://localhost:8081/messages"; // URL de tu API REST
    
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
        // Retransmitir el mensaje a todas las sesiones activas
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(message);
            }
            // Imprimir el mensaje recibido y retransmitido en la consola del servidor
            System.out.println("Mensaje de " + session.getId() + ": " + message.getPayload());
            //System.out.println("Mensaje retransmitido a " + webSocketSession.getId() + ": " + message.getPayload());

            MessageDto messageDto = new MessageDto(session.getId(), message.getPayload());
            restTemplate.postForObject(apiUrl, messageDto, MessageDto.class);
        }
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
