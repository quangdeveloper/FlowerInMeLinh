package vn.chohoa.flower.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import vn.chohoa.flower.util.Constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class RsDTO<T> {

    @JsonProperty(Constant.RESPONSE.RS)
    List<T> content = Collections.emptyList();

    public RsDTO(T content) {
        if (content != null) {
            this.content = Arrays.asList(content);
        }

    }

    public RsDTO(List<T> content) {
        if (content != null)
            this.content = content;
    }
}
