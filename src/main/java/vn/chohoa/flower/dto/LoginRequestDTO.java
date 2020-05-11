package vn.chohoa.flower.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;


}
