package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.RoleDTO;
import vn.chohoa.flower.dto.RoleNewDTO;
import vn.chohoa.flower.dto.RoleUpdateDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.RoleService;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getListRole(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(roleService.getAll(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createRole(@RequestBody @Valid RoleNewDTO r) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(roleService.createRole(r))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateRole(@RequestBody @Valid RoleUpdateDTO r) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(roleService.updateRole(r))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Long id) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(null)
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

}
