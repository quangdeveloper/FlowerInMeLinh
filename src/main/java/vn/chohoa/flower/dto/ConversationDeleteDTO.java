package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationDeleteDTO {

    private long id;

    private String code;

}
