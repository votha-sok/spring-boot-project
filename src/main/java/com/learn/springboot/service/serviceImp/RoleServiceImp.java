package com.learn.springboot.service.serviceImp;

import com.learn.springboot.entity.RoleEntity;
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
    public RoleEntity getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity save(RoleEntity role) {
        return roleRepository.save(role);
    }
}
