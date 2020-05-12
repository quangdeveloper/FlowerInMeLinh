package vn.chohoa.flower.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sun.plugin2.message.Conversation;
import vn.chohoa.flower.util.PartnerEnum;

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
public class User extends BaseModel {

    @NotNull
    private String userName;

    private String firebaseToken;

    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PartnerEnum partner = PartnerEnum.web;

    @NotNull
    private String password;

    private String avatar;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)//
    private Person person;

    @Builder.Default
    private Boolean isNeedChangePass = Boolean.FALSE;

    private Boolean isWarningSpam = Boolean.FALSE;

    private String reasonSpam;
//
//    @OneToOne(mappedBy = "sender,receiver")
//    private Conversation conversation;


}
