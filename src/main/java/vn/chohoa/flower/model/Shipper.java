package vn.chohoa.flower.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Shipper extends BaseModel {

    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private String email;
//    @OneToMany(mappedBy = "shipper",fetch = FetchType.LAZY)
//    List<OrderProduct> orderProducts = Collections.emptyList();
}
