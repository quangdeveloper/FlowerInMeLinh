package vn.chohoa.flower.controller;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.FarmDTO;
import vn.chohoa.flower.dto.FarmNewDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.GetListFarmParam;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.impl.FarmServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    private FarmServiceImpl farmService;

    @GetMapping
    public ResponseEntity<Object> getListFarm(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(farmService.findAll(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFarmByID(@PathVariable Long id) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(farmService.findByID(id))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getByCondition(@ApiParam @Valid GetListFarmParam p) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .map(farmService.findByNameAndOwnerAndIsActive(p))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Object> insertFarm(@RequestBody @Valid FarmNewDTO f) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder().code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .map(farmService.insertFarm(f))
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Object> updateGFarm(@RequestBody @Valid FarmDTO f) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .map(farmService.updateFarm(f))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletFarm(@PathVariable Long id) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(farmService.deleteFarm(id))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @GetMapping("/true")
    public ResponseEntity<Object> getFarmActive(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .map(farmService.findByIsActiveTrue(p))
                        .build()
        );
    }

    @GetMapping("/false")
    public ResponseEntity<Object> getFarmNoActive(@ApiParam @Valid PageParam p) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(farmService.findByIsActiveFalse(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }


}
