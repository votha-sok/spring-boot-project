package com.learn.springboot.service;

import com.learn.springboot.dto.role.RoleDto;
import com.learn.springboot.dto.role.RoleFeatureRequest;
import com.learn.springboot.entity.RoleEntity;


import java.util.List;

public interface RoleService {
    RoleEntity findById(Long id);
    List<RoleEntity> findAll();
    RoleEntity save(RoleEntity role);
    RoleEntity update(Long id ,RoleEntity role);
    RoleEntity applyRoleFeature(RoleFeatureRequest request);
}
