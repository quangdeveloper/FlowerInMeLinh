package vn.chohoa.flower.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RoleUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;
}
