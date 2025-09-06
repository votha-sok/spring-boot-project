package com.learn.springboot.dto.role;

import com.learn.springboot.entity.RoleEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class RoleInputRequest {
    Long id;
    @NotBlank(message = "Role name not allow null or empty .")
    String name;
    String description;

    public RoleEntity toRoleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        roleEntity.setName(name);
        roleEntity.setDescription(description);
        return roleEntity;
    }
}
