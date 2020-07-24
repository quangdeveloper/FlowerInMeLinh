package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private MessageType type;

    private String content;

    private String sender;

    public enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }
}
