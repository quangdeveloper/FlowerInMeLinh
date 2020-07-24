package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.DeleteParam;
import vn.chohoa.flower.dto.apiParam.GetConversationParam;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.service.impl.ConversationServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;


@RestController
@RequestMapping("/conversations")
public class ConverstionController {

    @Autowired
    private ConversationServiceImpl conversationService;

    //    @GetMapping
//    public ResponseEntity<ResponseDTO> findAllConversartion(@ApiParam @Valid PageParam con){
//
//        return ResponseEntity.ok().body(
//                ResponseDTO.builder()
//                        .map(conversationService.findAll(con))
//                        .code(Constant.RESPONSE.CODE.OK)
//                        .message(Constant.RESPONSE.MESSAGE.OK)
//                        .build()
//        );
//    }
    @GetMapping
    public ResponseEntity<ResponseDTO> findAllConversartionIsDeleteFalse(@ApiParam @Valid PageParam con) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.findAll(con))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }
//    @GetMapping
//    public ResponseEntity<ResponseDTO> findConsByUser(@ApiParam @Valid GetConversationParam p){
//
//        return ResponseEntity.ok().body(
//                ResponseDTO.builder()
//                        .map(conversationService.findbyUserId(p))
//                        .code(Constant.RESPONSE.CODE.OK)
//                        .message(Constant.RESPONSE.MESSAGE.OK)
//                        .build()
//        );
//    }


    @PostMapping
    public ResponseEntity<ResponseDTO> createNewConversartion(@RequestBody @Valid ConversationNewDTO con) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.createConversation(con))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @PutMapping("/users")
    public ResponseEntity<ResponseDTO> updateUserConversartion(@RequestBody @Valid UpdateUserConversation con) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.updateUser(con))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteConversartion(@RequestBody @Valid ConversationDeleteDTO con) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.deleteConversation(con))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteConversartion(@PathVariable long id) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.deleteConversationV2(DeleteParam.builder().id(id).build()))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }


}
