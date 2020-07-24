package vn.chohoa.flower.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.chohoa.flower.dto.ResponseDTO;

@RestController
@RequestMapping("/sms")
public class SMSController {

    private final String TOPIC_DESTINATION = "/topic/sms";

    @PostMapping
    public ResponseEntity<ResponseDTO> sendMessage() {
        return null;
    }
}
