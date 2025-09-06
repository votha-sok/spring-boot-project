package com.learn.springboot.service;

import com.learn.springboot.dto.role.RoleDto;
import com.learn.springboot.entity.RoleEntity;


import java.util.List;

public interface RoleService {
    RoleDto findById(Long id);
    List<RoleDto> findAll();
    RoleDto save(RoleEntity role);
    RoleDto update(Long id ,RoleEntity role);

}
