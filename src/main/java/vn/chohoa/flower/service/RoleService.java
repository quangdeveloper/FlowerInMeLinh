package vn.chohoa.flower.service;

import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.RoleNewDTO;
import vn.chohoa.flower.dto.RoleUpdateDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;

public interface RoleService {

    ActionDTO createRole(RoleNewDTO r);

    ActionDTO updateRole(RoleUpdateDTO role);

    PageDTO getAll(PageParam p);
}
