package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.MojiNewDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.MojiService;
import vn.chohoa.flower.service.impl.MojiServicesImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/moji")
public class MojiController {

    @Autowired
    private MojiServicesImpl mojiServices;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllMoji(@ApiParam @Valid PageParam p){

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                .map(mojiServices.findAll(p))
                .code(Constant.RESPONSE.CODE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> findAllMoji(@RequestBody @Valid MojiNewDTO moji){

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(mojiServices.createMoji(moji))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }
}
