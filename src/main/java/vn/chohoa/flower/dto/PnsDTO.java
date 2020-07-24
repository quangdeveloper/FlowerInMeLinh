package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PnsDTO {

    private String token;
    private String content;
}
