package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.FlowerNewDTO;
import vn.chohoa.flower.dto.PageDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.impl.FlowerCatgoryServiceImpl;
import vn.chohoa.flower.service.impl.FlowerServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    @Autowired
    private FlowerServiceImpl flowerService;

    @GetMapping
    public ResponseEntity<Object> getAll(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(flowerService.findAll(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );

    }

    @PostMapping
    public ResponseEntity<Object> createFlower(@RequestBody @Valid FlowerNewDTO f) {

        return ResponseEntity.ok().body(

                ResponseDTO.builder()
                        .map(flowerService.createFlower(f))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()

        );
    }

    @PutMapping
    public ResponseEntity<Object> updateFlower(@RequestBody @Valid FlowerNewDTO f) {

        return ResponseEntity.ok().body(

                ResponseDTO.builder()
                        .map(flowerService.updateFlower(f))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()

        );
    }


    @GetMapping("/true")
    public ResponseEntity<Object> getFlowerSaling(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(

                ResponseDTO.builder()
                        .map(flowerService.findByIsSale(p, true))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @GetMapping("/false")
    public ResponseEntity<Object> getFlowerUnSaling(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(

                ResponseDTO.builder()
                        .map(flowerService.findByIsSale(p, false))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }
}
