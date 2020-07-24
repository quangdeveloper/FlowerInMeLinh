package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemDTO {

    private String name;
    private Long orderBy;
    private Long parentId;
    private Boolean isSystemPermission;
    private String path;
    private String classStyle;
}
