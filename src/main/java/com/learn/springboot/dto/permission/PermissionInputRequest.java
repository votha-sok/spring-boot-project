package com.learn.springboot.dto.permission;

import com.learn.springboot.entity.PermissionEntity;

public class PermissionInputRequest extends PermissionDto {
    public PermissionEntity toDto() {
        PermissionEntity permissionEntity1 = new PermissionEntity();
        permissionEntity1.setId(getId());
        permissionEntity1.setFunctionName(getFunctionName());
        permissionEntity1.setFunctionOrder(getFunctionOrder());
        return permissionEntity1;
    }
}
