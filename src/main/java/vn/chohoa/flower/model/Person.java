package vn.chohoa.flower.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@ToString(exclude = {"user"})
public class Person extends BaseModel{

    @NotNull
    private String fullName;

    private LocalDate birthday;

    @Size(max = 20)

    private String phone;

    @Size(max = 1000)

    private String avatar;

    @Size(max = 1000)
    private String email;

    @OneToOne(mappedBy = "person")
    private  User user;








}
