package com.learn.springboot.service;

import com.learn.springboot.entity.RoleEntity;


import java.util.List;

public interface RoleService {
    RoleEntity getRoleById(Long id);
    List<RoleEntity> getAllRoles();
    RoleEntity save(RoleEntity role);

}
