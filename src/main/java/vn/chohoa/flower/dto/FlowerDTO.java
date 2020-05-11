package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.chohoa.flower.model.Farm;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlowerDTO {

    private long id;

    private String name;

    private String color;

    private Long prize;

    private Long amount;

    private Long type;

    private Boolean isSale;

    private String address;

    private String nameFarm;

}
