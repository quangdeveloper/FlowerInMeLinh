package vn.chohoa.flower.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String userId;

    private String username;

    private String password;

    private String fullname;

    private LocalDate birthday;

    private String role;

    private String hasAvatar;

    private String personId;

    private String email;

    private int warningSpam;

    private String token;

    private List<RolePermissionDetailDTO> roles = new ArrayList<>();
}
