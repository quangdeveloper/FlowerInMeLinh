package vn.chohoa.flower.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserConversation {

    @NotNull
    private long id;

    @NotNull
    private Boolean flag;

    @NotNull
    private List<Long>  ids = new ArrayList<>();
}
