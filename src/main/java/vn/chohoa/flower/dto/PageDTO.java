package vn.chohoa.flower.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;

@Builder
@Data
public class PageDTO {

    @JsonProperty("rs")
    @Builder.Default
    private Object content = Collections.emptyList();

    @JsonProperty("PO_TOTAL")
    private long total = 0;

}
