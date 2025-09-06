package com.learn.springboot.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long roleId;
    private String roleName;
}
