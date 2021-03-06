package vn.chohoa.flower.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.chohoa.flower.util.PartnerEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    @Builder.Default
    private Boolean isNeedChangePass = Boolean.FALSE;

    private Boolean isWarningSpam = Boolean.FALSE;

    private String reasonSpam;

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    List<Conversation> conversations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "receivers")
    @JsonIgnore
    private List<LogEmail> logEmails;
}
