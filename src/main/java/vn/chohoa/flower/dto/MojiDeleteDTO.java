package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MojiDeleteDTO {

    private long id;

    private String name;

    private String code;
}
