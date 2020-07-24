package vn.chohoa.flower.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.*;
import vn.chohoa.flower.dto.apiParam.DocumentParam;
import vn.chohoa.flower.service.FCMService;
import vn.chohoa.flower.service.NotificationService;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private FCMService fcmService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<ResponseDTO> sendNotification(@RequestBody NotificationNewDTO p) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(fcmService.sendMessage(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }


    @PutMapping
    public ResponseEntity<ResponseDTO> updateNotification(@RequestBody NotificationUpdateDTO p) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(fcmService.updateNotification(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }


    @GetMapping
    public ResponseEntity<ResponseDTO> getNotification(@ApiParam @Valid DocumentParam doc) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(fcmService.getDocument(doc))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }


    @PostMapping("/test")
    public String sendNotification(@RequestBody PnsDTO pnsDTO) {
        return fcmService.pushNotification(pnsDTO);

    }

    @PostMapping("/test/createPerson")
    public ResponseEntity<ResponseDTO> createPerson(@RequestBody PersonDTO p) {
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .map(fcmService.savePersonDetailDTO(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );
    }

}
