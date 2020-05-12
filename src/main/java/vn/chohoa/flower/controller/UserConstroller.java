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
import vn.chohoa.flower.dto.LoginResponseDTO;
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

        /** lấy authentication ra để tạo theo kiểu token*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );

        /** thiết lập authentication */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /** mỗi lần user đăng sẽ sinh ra 1 token mới nên pahir update token nay vào thông tin
         * user để trong quá trình user thao tác data lấy đc token mới này
         */
        final User user = userService.updateToken(
                userLogin.getUsername(),
                userLogin.getPartner().getType(),
                userLogin.getFirebaseToken()
        );

        /** tạo mới 1 response để trả data ra*/
        final LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setUserId(user.getId().toString());

        loginResponseDTO.setUsername(user.getUserName());

        loginResponseDTO.setFullname(user.getPerson().getFullName());

        loginResponseDTO.setBirthday(user.getPerson().getBirthday());

        loginResponseDTO.setEmail(user.getPerson().getEmail());

        loginResponseDTO.setPersonId(user.getPerson().getId().toString());


        return null;
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
