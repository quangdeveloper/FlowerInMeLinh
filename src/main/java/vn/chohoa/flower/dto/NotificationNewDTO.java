package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class NotificationNewDTO {

    @NotNull
    private String content;

    @NotNull
    private Integer type;

    @NotNull
    @Size(max = 500)
    private String title;
}
