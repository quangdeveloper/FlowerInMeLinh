package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.chohoa.flower.model.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserNewDTO {

    @Size(max = 1000)
    @NotNull
    private String userName;

    @NotNull
    @Size(max = 255)
    private String  password;

    @NotEmpty
    private List<Role> roles= new ArrayList<>();

    @NotNull
    private PersonNewDTO personNewDTO;

}
