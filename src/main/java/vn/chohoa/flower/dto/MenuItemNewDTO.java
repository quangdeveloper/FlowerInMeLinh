package vn.chohoa.flower.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuItemNewDTO {

    @NotNull
    private String name;
    @NotNull
    private Long orderBy;

    private Long parentId;

    private Boolean isSystemPermission;

    private String path;
    private String classStyle;
}
