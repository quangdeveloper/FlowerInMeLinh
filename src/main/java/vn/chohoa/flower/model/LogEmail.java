package vn.chohoa.flower.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import vn.chohoa.flower.util.Constant;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEmail extends BaseModel {

    private String subject;

    private String content;

    @ToString.Exclude
    @OneToMany(mappedBy = "logEmail",cascade = CascadeType.PERSIST)
    private List<AttackFileMail> attackFileMails;

    @ManyToMany
    @ToString.Exclude
    private List<User> receivers;

    @Builder.Default
    private Boolean status = Boolean.FALSE;

    @Builder.Default
    private String type = Constant.TypeEmail.MIME;
}
