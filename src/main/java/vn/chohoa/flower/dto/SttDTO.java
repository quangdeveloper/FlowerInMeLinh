package vn.chohoa.flower.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class SttDTO extends IsActiveField {

    private long stt;
}
