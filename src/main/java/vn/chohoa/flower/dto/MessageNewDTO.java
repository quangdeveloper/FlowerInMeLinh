package vn.chohoa.flower.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageNewDTO {

    @NotNull
    private Long senderId;

    @NotNull
    private String senderUsername;

    @NotNull
    private List<Long> userIds;//danh sach user tham gia hoi thoai


    private String content;

    private String contentFile;

    private Long idMoji;

}
