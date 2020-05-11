package vn.chohoa.flower.controller;


import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.chohoa.flower.dto.LoginRequestDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.dto.UserNewDTO;
import vn.chohoa.flower.dto.apiParam.PageParam;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.service.impl.UserServiceImpl;
import vn.chohoa.flower.util.Constant;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserConstroller {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(
            @RequestBody @Valid final LoginRequestDTO userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User user = userService.


    }

    @GetMapping

    public ResponseEntity<ResponseDTO> getListUser(@ApiParam @Valid PageParam p) {

        return ResponseEntity.ok().body(

                ResponseDTO.builder()
                        .map(userService.getListUser(p))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()
        );

    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid UserNewDTO u) {

        return ResponseEntity.ok().body(

                ResponseDTO.builder()
                        .map(userService.createUser(u))
                        .code(Constant.RESPONSE.CODE.OK)
                        .message(Constant.RESPONSE.MESSAGE.OK)
                        .build()

        );
    }

}
