package vn.chohoa.flower.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = {"role", "person", "imployee", ""})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "person_id")})
public class User extends BaseModel{

    @NotNull
    private String userName;

    private String fireBase;


    @NotNull
    private String password;

    private String avatar;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)//
    private Person person;


}
