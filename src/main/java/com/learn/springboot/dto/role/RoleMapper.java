package com.learn.springboot.dto.role;

import com.learn.springboot.entity.RoleEntity;

import javax.management.relation.Role;

public class RoleMapper {
    public static RoleDto toDto(RoleEntity role){
        return new RoleDto(role.getId(), role.getName(), role.getDescription());
    }
}
