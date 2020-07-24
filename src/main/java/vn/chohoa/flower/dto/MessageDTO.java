package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageDTO {

    private Long id;

    private String code;

    private String createdByUsername;

    private String content;

    private String fileContent;

    private Long moji;

    private LocalDateTime createdAt;

    private LocalDateTime updatAt;

    private Boolean isDelete;

    private Boolean isSpam;

}
