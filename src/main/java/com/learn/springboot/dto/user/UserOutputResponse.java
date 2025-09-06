package com.learn.springboot.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserOutputResponse extends UserDto {
    Set<UserRoleDto> roles;

    public UserOutputResponse(Long id, String userName, String email, String phone, Set<UserRoleDto> roles) {
        super(id, userName, email, phone);
        this.roles = roles;
    }
}
