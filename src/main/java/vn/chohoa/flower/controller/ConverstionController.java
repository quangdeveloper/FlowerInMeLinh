package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.GetListConversationParam;
import vn.chohoa.flower.service.impl.ConversationServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/conversations")
public class ConverstionController {

    @Autowired
    private ConversationServiceImpl conversationService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createMessage(@RequestBody @Valid MessageNewDTO mess) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.createMessage(mess))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getConversation(@ApiParam GetListConversationParam p) {

        return ResponseEntity.ok().body(ResponseDTO.builder()
                .map(conversationService.findConversationByCode(p))
                .code(Constant.RESPONSE.CODE.OK)
                .message(Constant.RESPONSE.MESSAGE.OK)
                .build()
        );


    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateMessage(@RequestBody @Valid MessageUpdateDTO mess) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.updateContentMessage(mess))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @PutMapping("/view")
    public ResponseEntity<ResponseDTO> isViewMessage(@RequestBody @Valid MessageViewerDTO mess) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.isViewMessage(mess))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDTO> deleteMessage(@RequestBody @Valid MessageDeleteDTO mess) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(conversationService.deleteContentMessage(mess))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }


}
