package vn.chohoa.flower.dto.apiParam;

import lombok.Data;

@Data
public class GetListFarmParam extends PageParam{

    private String name;

    private String address;

    private Boolean isActive;

    private String owner;

}
