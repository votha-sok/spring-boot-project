package com.learn.springboot.service;


import com.learn.springboot.entity.PermissionEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public interface PermissionService {
    PermissionEntity save(PermissionEntity feature);
    List<PermissionEntity> findAll();
    PermissionEntity findById(Long id);
    PermissionEntity update(Long id, PermissionEntity request);
}
