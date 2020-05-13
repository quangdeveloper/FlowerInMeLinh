package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageUpdateDTO {
    /**
     * chi cho phep chinhr sửa content dang string
     * còn moji và hình ảnh xóa luôn
     */
    @NotNull
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private Long editorId;

    @NotNull
    private String editorUsername;

    private String content;

}
