package vn.chohoa.flower.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MojiUpdateDTO {

    private  long id;

    private String name;

    private String code;

    private String linkMoji;

    private long groupId;

    private String groupName;
}
