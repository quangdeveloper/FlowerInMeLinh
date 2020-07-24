package vn.chohoa.flower.controller;


import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.MessageDeleteDTO;
import vn.chohoa.flower.dto.MessageNewDTO;
import vn.chohoa.flower.dto.MessageUpdateDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.DeleteParam;
import vn.chohoa.flower.dto.apiParam.GetConversationParam;
import vn.chohoa.flower.service.impl.MessageServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private MessageServiceImpl messageService;


    @PostMapping
    public ResponseEntity<ResponseDTO> createMessage(@RequestBody @Valid MessageNewDTO mess) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(messageService.createMessage(mess))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getConversation(@ApiParam GetConversationParam p) {

        return ResponseEntity.ok().body(ResponseDTO.builder()
                .map(messageService.findConversationByCode(p))
                .code(Constant.RESPONSE.CODE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()
        );
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateMessage(@RequestBody @Valid MessageUpdateDTO mess) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(messageService.updateContentMessage(mess))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

//    @PutMapping("/view")
//    public ResponseEntity<ResponseDTO> isViewMessage(@RequestBody @Valid MessageViewerDTO mess) {
//        return ResponseEntity.ok().body(
//                ResponseDTO.builder()
//                        .map(conversationService.isViewMessage(mess))
//                        .code(Constant.RESPONSE.CODE.OK)
//                        .message(Constant.RESPONSE.MESSAGE.OK)
//                        .build()
//        );
//    }
//
//    @DeleteMapping
//    public ResponseEntity<ResponseDTO> deleteMessage(@RequestBody @Valid MessageDeleteDTO mess) {
//        return ResponseEntity.ok().body(
//                ResponseDTO.builder()
//                        .map(messageService.deleteContentMessage(mess))
//                        .code(Constant.RESPONSE.CODE.OK)
//                        .message(Constant.RESPONSE.MESSAGE.OK)
//                        .build()
//        );
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteMessage(@PathVariable long id) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(messageService.deleteMessage(DeleteParam.builder().id(id).build()))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

}
