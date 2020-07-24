package vn.chohoa.flower.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseModel {

    private String name;
    private Long orderBy;
    private Long parentId;
    private Boolean isSystemPermission;
    private String path;
    private String classStyle;

}
