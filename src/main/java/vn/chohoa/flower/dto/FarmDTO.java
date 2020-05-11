package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmDTO {

    private Long id;

    private String name;

    private String code;

    private String address;

    private String owner;

    private String phoneNumber;

    private Boolean isActive;


}
