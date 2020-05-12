package vn.chohoa.flower.dto;

import lombok.Data;

@Data
public class FuntionPermissionDTO {

    private String name;

    private Long roleId;

    private Long functionId;

    public Boolean isView = Boolean.FALSE;

    public Boolean isAdd = Boolean.FALSE;

    public Boolean isEdit = Boolean.FALSE;

    public Boolean isDelete = Boolean.FALSE;

}
