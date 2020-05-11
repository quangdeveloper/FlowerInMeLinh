package vn.chohoa.flower.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.chohoa.flower.dto.jsonPares.CustomLocalDateDeserializer;
import vn.chohoa.flower.dto.jsonPares.CustomLocalDateSerializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonNewDTO {

    @NotNull
    @Size(max = 255)
    private String fullName;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @NotNull
    private LocalDate birthday;

    @Size(max = 20)
    @NotNull
    private String phone;

    @Size(max = 1000)
    private String avatar;

    @Size(max = 100)
    private String email;

}
