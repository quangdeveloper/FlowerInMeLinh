package vn.chohoa.flower.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;
import vn.chohoa.flower.response.ResponseConfig;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"map", "code", "message"})
public class ResponseDTO {

    @JsonIgnore
    private Object map;

    private String code;

    private String message;

    @JsonProperty("map")
    public Object getBody() {

        if (map != null && map instanceof ActionDTO) {//xu update, insert, delete
            return ((ActionDTO) map).getValue();
        }
        if (!StringUtils.hasText(message)) {//xu truong TH ko co message

            String mess = ResponseConfig.getInstance().getMess(this.code);
            if (mess != null) {
                this.message = mess;
            }
        }
        //mac dinh la se return lai gia tri dc gan
        return map;
    }

}
