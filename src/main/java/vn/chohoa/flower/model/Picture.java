package vn.chohoa.flower.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Picture extends BaseModel {

    private String description;

    private String linkImage;

}
