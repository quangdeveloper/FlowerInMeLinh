package vn.chohoa.flower.dto.apiParam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonPropertyOrder({"collection","documentName"})
@NoArgsConstructor
@AllArgsConstructor
public class DocumentParam {

    private String collection;

    private String documentName;
}
