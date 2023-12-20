package sd.proyecto2.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.WebSocketHandler; // Esta es la importación que podría faltar
import sd.proyecto2.chatapp.websocket.MyWebSocketHandler; // Asegúrate de que el paquete sea correcto


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/ws").setAllowedOrigins("*"); // Permitir todos los orígenes
        // Para una mejor seguridad, reemplaza "*" con los orígenes específicos que deseas permitir
    }

    // Aquí puedes definir tu propio WebSocketHandler
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }
}
