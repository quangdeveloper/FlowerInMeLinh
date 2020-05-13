package vn.chohoa.flower.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {

    private long id;

    private String userName;

    private String fullName;

    private String birthday;

    private String email;

    private String phone;

    private String avatar;
}
