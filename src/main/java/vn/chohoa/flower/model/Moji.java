package vn.chohoa.flower.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Moji extends BaseModel {

    @NotNull
    private String code;

    @NotNull
    private String name;

    private Long groupId;

    private String groupName;

    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "moji")
    private List<Conversation> conversations = new ArrayList<>();
}
