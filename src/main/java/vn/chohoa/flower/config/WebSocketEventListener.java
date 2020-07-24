package vn.chohoa.flower.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import vn.chohoa.flower.dto.ChatMessage;

@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketConnection(SessionConnectedEvent event) {
        logger.info("Recieved a new WebSocket connection");
    }
    @EventListener
    public void handleWebSocketDisConnection(SessionDisconnectEvent event){

        StompHeaderAccessor accessor =StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username");
        if (username!= null){

            logger.info("user disconect: "+username);
            ChatMessage message = new ChatMessage();
            message.setType(ChatMessage.MessageType.LEAVE);
            message.setSender(username);
            messageTemplate.convertAndSend("topic/publicChatRoom",message);
        }
    }
}
