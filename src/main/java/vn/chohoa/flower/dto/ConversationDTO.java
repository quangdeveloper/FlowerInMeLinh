package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ConversationDTO {

    private String id;

    private String code;

    private String senderUsername;

    private String content;

    private String fileContent;

    private LocalDateTime sendedTime;

    private LocalDateTime editTime;

    private Boolean isDelete;

    private List<UserConversationDTO> users = new ArrayList<>();

}
