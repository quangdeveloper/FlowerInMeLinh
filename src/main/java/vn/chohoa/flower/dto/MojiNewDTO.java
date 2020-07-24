package vn.chohoa.flower.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class MojiNewDTO {

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("link")
    private String linkMoji;

    private long groupId;

    private String groupName;
}
