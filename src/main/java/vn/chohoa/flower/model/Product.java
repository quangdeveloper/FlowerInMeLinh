package vn.chohoa.flower.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (fetch = FetchType.LAZY)
    private Flower flower;

    private Long prize;

    private Long promotion;

    private Long amount ;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderProduct orderProduct;

    @Builder.Default
    private Boolean isActive =Boolean.TRUE;

}
