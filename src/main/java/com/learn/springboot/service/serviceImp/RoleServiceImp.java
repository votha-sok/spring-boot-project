package com.learn.springboot.service.serviceImp;

import com.learn.springboot.dto.role.RoleDto;
import com.learn.springboot.dto.role.RoleMapper;
import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.exception.ResourceNotFoundException;
import com.learn.springboot.repository.RoleRepository;
import com.learn.springboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDto findById(Long id) {
        return RoleMapper.toDto(roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found")));
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(RoleMapper::toDto).toList();
    }

    @Override
    public RoleDto save(RoleEntity role) {
        return RoleMapper.toDto(roleRepository.save(role));
    }

    @Override
    public RoleDto update(Long id, RoleEntity role) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
        roleEntity.setName(role.getName());
        roleEntity.setDescription(role.getDescription());
        return RoleMapper.toDto(roleRepository.save(role));
    }
}
