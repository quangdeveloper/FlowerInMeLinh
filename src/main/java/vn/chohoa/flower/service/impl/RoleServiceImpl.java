package vn.chohoa.flower.service.impl;

import com.google.common.collect.ImmutableMap;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.exception.DataExistsException;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.mapper.RoleMapper;
import vn.chohoa.flower.model.Role;
import vn.chohoa.flower.repository.RoleRepository;
import vn.chohoa.flower.service.RoleService;
import vn.chohoa.flower.util.Constant;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ActionDTO createRole(RoleNewDTO r) {

        String code = makeCode(r.getName());

        Role role = roleRepository.findByCode(code);

        if (role != null) {
            throw new GeneralException(
                    Constant.RESPONSE.CODE.C409,
                    Constant.RESPONSE.MESSAGE.C409_ROLE);

        }

        role = roleMapper.toRoleFromRoleNewDTO(r);

        role.setCode(code);

        role = roleRepository.save(role);

        return new ActionDTO(ImmutableMap.builder()
                .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE, role.getId())
                .build()
        );
    }

    @Override
    public ActionDTO updateRole(RoleUpdateDTO role) {

        Role r = roleRepository.findById(role.getId()).orElseThrow(
                ()-> new  GeneralException(Constant.RESPONSE.CODE.C404,
                        Constant.RESPONSE.MESSAGE.C404_ROLE)
        );

        String code = makeCode(role.getName());

        r = roleMapper.toRoleFromRoleUpdateDTO(role);

        r.setCode(code);

        return new ActionDTO(ImmutableMap.builder()
        .put(Constant.RESPONSE.JSON_KEY.RETURN_VALUE,roleRepository.save(r).getId())
        .build());
    }

    @Override
    public PageDTO getAll(PageParam p) {

        Pageable pageable = PageRequest.of(
                p.getPageNo() - 1,
                p.getPageSize(),
                Sort.by("id").descending()
        );

        final Page<Role> page = roleRepository.findAll(pageable);

        List<RoleDTO> list = page.map(roleMapper::toRoleDTOFromRole).getContent();

        final long total = page.getTotalElements();

        return PageDTO.builder()
                .content(list)
                .total(total)
                .build();
    }

    private String makeCode(String name) {

        String[] names = name.trim().split(" ");

        StringBuilder str = new StringBuilder();

        for (String i : names){

            str.append(i.substring(0,1));
        }
        return str.toString().toUpperCase();

    }
}
