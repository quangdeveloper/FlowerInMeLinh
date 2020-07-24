package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.chohoa.flower.model.Product;
import vn.chohoa.flower.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderNewDTO {

    @NotNull
    private String fullname;

    @NotNull
    private String addressOrder;

    @NotNull
    private String phone;

    @NotNull
    @Email
    private String email;

    private List<ProductNewDTO> products = Collections.emptyList();

}
