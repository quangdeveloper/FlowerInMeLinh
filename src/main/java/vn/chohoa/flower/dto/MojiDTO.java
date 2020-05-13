package vn.chohoa.flower.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MojiDTO {

    private Long id;

    private String code;

    private String name;

    private Long groupId;

    private String groupName;

    private String content;

}
