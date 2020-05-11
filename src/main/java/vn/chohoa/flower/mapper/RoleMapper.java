package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import vn.chohoa.flower.dto.RoleDTO;
import vn.chohoa.flower.dto.RoleNewDTO;
import vn.chohoa.flower.dto.RoleUpdateDTO;
import vn.chohoa.flower.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRoleFromRoleNewDTO(RoleNewDTO r);

    RoleDTO toRoleDTOFromRole(Role role);

    Role toRoleFromRoleUpdateDTO(RoleUpdateDTO r);
}
