package vn.chohoa.flower.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class OrderProduct extends BaseModel {


    private String addressOrder;

    private String fullname;

    private String phone;

    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "orderProduct")
    private List<Product> products = Collections.emptyList();

}
