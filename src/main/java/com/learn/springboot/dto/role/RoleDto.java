package com.learn.springboot.dto.role;

import com.learn.springboot.dto.user.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto   {
    private Long roleId;
    private String roleName;
    private String description;
}
