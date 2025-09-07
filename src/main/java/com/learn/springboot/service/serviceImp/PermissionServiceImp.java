package com.learn.springboot.service.serviceImp;

import com.learn.springboot.entity.PermissionEntity;
import com.learn.springboot.exception.ResourceNotFoundException;
import com.learn.springboot.repository.PermissionRepository;
import com.learn.springboot.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImp implements PermissionService {

    private final PermissionRepository permissionRepository;
    @Override
    public PermissionEntity save(PermissionEntity body) {
        return permissionRepository.save(body);
    }

    @Override
    public List<PermissionEntity> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public PermissionEntity findById(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission Not Found"));
    }

    @Override
    public PermissionEntity update(Long id, PermissionEntity request) {
        PermissionEntity permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission Not Found"));
        permission.setFunctionOrder(request.getFunctionOrder());
        permission.setFunctionName(request.getFunctionName());
        return permissionRepository.save(permission);
    }
}
