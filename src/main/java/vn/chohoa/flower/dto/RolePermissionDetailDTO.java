package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class RolePermissionDetailDTO {

    private RoleCDTO role;

    private List<FuntionPermissionDTO> permissions = Collections.emptyList();

}
