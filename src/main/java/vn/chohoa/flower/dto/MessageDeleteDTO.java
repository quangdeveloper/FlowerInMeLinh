package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class MessageDeleteDTO {

    @NotNull
    private Long id;

    @NotNull
    private String code;


}
