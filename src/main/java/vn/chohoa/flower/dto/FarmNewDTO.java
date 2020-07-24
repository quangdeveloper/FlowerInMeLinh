package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class FarmNewDTO {

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String owner;

    @NotNull
    private String phoneNumber;

    private Boolean isActive;


}
