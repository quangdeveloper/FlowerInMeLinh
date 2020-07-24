package vn.chohoa.flower.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttackFileMail extends BaseModel{

    private String path;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private LogEmail logEmail;
}
