package vn.chohoa.flower.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Role extends BaseModel{

    @NotNull
    @EqualsAndHashCode.Include
    private String code;

    @NotNull
    private String name;

    private String description;

    private Boolean isSystemPermission = Boolean.FALSE;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    List<User> users;


}
