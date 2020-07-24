package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.MenuItemNewDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.MenuService;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(@Valid @ApiParam PageParam p) {

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .map(menuService.getMenu(p))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createMenu(@RequestBody @Valid MenuItemNewDTO menu) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .map(menuService.createMenu(menu))
                        .build()
        );
    }
}
