package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageViewerDTO {
    private Long id;

    private String code;

    private Long viewerId;

    private String viewerUsername;
}
