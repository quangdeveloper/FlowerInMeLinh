package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlowerNewDTO {

    private long id;

    private String name;

    private String color;

    private Long prize;

    private Long amount;

    private Boolean isSale;

    private Long farmId;

    private Long cateId;
}
