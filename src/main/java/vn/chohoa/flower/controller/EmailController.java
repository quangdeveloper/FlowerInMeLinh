package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.EmailNewDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.repository.LogEmailRepository;
import vn.chohoa.flower.service.LogEmailService;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private LogEmailService emailService;

    @PostMapping
    public ResponseEntity<ResponseDTO> sendSimpleEmail(@RequestBody EmailNewDTO e) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(emailService.insertLogEmail(e))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

    @PostMapping("/mine")
    public ResponseEntity<ResponseDTO> sendMimeEmail(@ModelAttribute("email") EmailNewDTO email) {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(emailService.insertLogEmail(email))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }
}
