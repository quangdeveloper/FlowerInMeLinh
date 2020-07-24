package vn.chohoa.flower.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageNewDTO {

    private String content;

    private String fileContent;

    private Long moji;

    @NotNull
    private long id;

}
