package vn.chohoa.flower.dto;

import lombok.Builder;
import lombok.Data;
import vn.chohoa.flower.model.Flower;
import vn.chohoa.flower.model.OrderProduct;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductNewDTO {

    @NotNull
    private Long flowerId;

    @NotNull
    private Long prize;

    @NotNull
    private Long promotion;

    @NotNull
    private Long amount;

}
