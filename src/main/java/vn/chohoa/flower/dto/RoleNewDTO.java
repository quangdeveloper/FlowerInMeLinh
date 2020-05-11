package vn.chohoa.flower.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleNewDTO {

    @NotNull
    private String name;

    @NotNull
    @Builder.Default
    private Boolean isSystemPermission = Boolean.TRUE;

    private String description;
}
