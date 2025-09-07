package com.learn.springboot.dto.permission;

import com.learn.springboot.entity.PermissionEntity;

public class PermissionMapper {

    public static PermissionDto toDto(PermissionEntity permission) {
        return new PermissionDto(permission.getId(), permission.getFunctionName(), permission.getFunctionOrder());
    }
}
