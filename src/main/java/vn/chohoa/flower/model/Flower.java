package vn.chohoa.flower.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Flower extends BaseModel{

    @NotNull
    private String name;

    private String color;

    private Long prize;

    private Long promotion;

    private Long amount ;

    @Builder.Default
    private Boolean isSale = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    private FlowerCategory flowerCategory;


}
