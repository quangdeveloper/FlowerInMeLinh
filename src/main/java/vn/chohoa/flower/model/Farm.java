package vn.chohoa.flower.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String code;

    @NotNull
    @Column
    private String name;

    private String address;

    private String owner;

    private String phoneNumber;

    private Boolean isActive = Boolean.TRUE;

    @OneToMany (mappedBy = "farm",fetch = FetchType.LAZY)
    private List<Flower> flowers;
}
