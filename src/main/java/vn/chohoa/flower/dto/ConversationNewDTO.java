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
public class ConversationNewDTO {

    @NotNull
    private String name;

    /** sach user tham gia hoi thoai*/
    @NotNull
    private List<Long> userIds;

}
