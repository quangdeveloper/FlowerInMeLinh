package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDeleteDTO {

    private Long id;

    private String code;

    private Long editorId;

    private String editorUsername;

}
