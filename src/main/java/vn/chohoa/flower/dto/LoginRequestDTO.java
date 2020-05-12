package vn.chohoa.flower.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.chohoa.flower.util.PartnerEnum;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @ApiModelProperty
    @NotNull
    private PartnerEnum partner = PartnerEnum.web;

    @JsonProperty("firebase_token")
    private String firebaseToken;


}
