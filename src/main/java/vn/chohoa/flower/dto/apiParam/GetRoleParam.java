package vn.chohoa.flower.dto.apiParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetRoleParam{

    private String code;

    private String name;

    @Builder.Default
    private Boolean isSystemPermission= Boolean.TRUE;

}
