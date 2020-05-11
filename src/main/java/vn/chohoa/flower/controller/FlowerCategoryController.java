package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.FlowerCategoryDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.impl.FlowerCatgoryServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/flowercategories")
public class FlowerCategoryController {

    @Autowired
    FlowerCatgoryServiceImpl flowerCatgoryService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllFlowerCategory(@ApiParam @Valid  PageParam p) {

        return ResponseEntity.ok().body(ResponseDTO.builder()
                .map(flowerCatgoryService.getAllFlowerCategory(p))
                .code(Constant.RESPONSE.CODE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()

        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByID(@PathVariable Long id){

        return ResponseEntity.ok().body(ResponseDTO.builder()
                .map(flowerCatgoryService.findById(id))
                .code(Constant.RESPONSE.CODE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()

        );
    }

    @PostMapping
    public ResponseEntity<Object> insertCategory(@RequestBody @Valid  FlowerCategoryDTO f)
    {
        return ResponseEntity.ok().body( ResponseDTO.builder()
                .map(flowerCatgoryService.insertCate(f))
                .code(Constant.RESPONSE.MESSAGE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()
        );
    }


    @PutMapping
    public ResponseEntity<Object> updateCategory(@RequestBody @Valid  FlowerCategoryDTO f)
    {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                .map(flowerCatgoryService.updateCategoryFlower(f))
                .code(Constant.RESPONSE.CODE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()
        );
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteCate(@PathVariable Long id){
//
//        return ResponseEntity.ok().body(
//                ResponseDTO.builder()
//                .map(flowerCatgoryService.delete(id))
//                .code(Constant.RESPONSE.CODE.OK)
//                .message(Constant.RESPONSE.MESSAGE.OK)
//                .build()
//        );
//    }
}
