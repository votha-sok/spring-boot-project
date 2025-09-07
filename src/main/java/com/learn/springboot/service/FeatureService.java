package com.learn.springboot.service;

import com.learn.springboot.dto.feature.FeatureOutputResponse;
import com.learn.springboot.dto.feature.FeaturePermissionRequest;
import com.learn.springboot.dto.feature.FeaturePermissionResponse;
import com.learn.springboot.dto.permission.PermissionInputRequest;
import com.learn.springboot.entity.FeatureEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeatureService {
    FeatureEntity save(FeatureEntity feature);
    List<FeatureEntity> findAll();
    FeatureEntity findById(Long id);
    FeatureEntity update(Long id, FeatureEntity request);
    FeatureEntity applyPermissions(FeaturePermissionRequest request);
    List<FeaturePermissionResponse> getPermissions(Long id);
}
