package vn.chohoa.flower.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    @NotNull
    private long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String description;

    private Boolean isSystemPermission;


}
