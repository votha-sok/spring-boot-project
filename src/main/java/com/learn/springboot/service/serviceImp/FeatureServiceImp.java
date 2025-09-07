package com.learn.springboot.service.serviceImp;

import com.learn.springboot.dto.feature.FeatureOutputResponse;
import com.learn.springboot.dto.feature.FeaturePermissionRequest;
import com.learn.springboot.dto.feature.FeaturePermissionResponse;
import com.learn.springboot.dto.permission.PermissionDto;
import com.learn.springboot.entity.*;
import com.learn.springboot.exception.ResourceNotFoundException;
import com.learn.springboot.repository.FeatureRepository;
import com.learn.springboot.repository.PermissionRepository;
import com.learn.springboot.repository.RoleRepository;
import com.learn.springboot.repository.UserRepository;
import com.learn.springboot.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureServiceImp implements FeatureService {
    private final FeatureRepository featureRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public FeatureEntity save(FeatureEntity feature) {
        return featureRepository.save(feature);
    }

    @Override
    public List<FeatureEntity> findAll() {
        return featureRepository.findAll();
    }

    @Override
    public FeatureEntity findById(Long id) {
        return featureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feature not found"));
    }

    @Override
    public FeatureEntity update(Long id, FeatureEntity request) {
        FeatureEntity feature = featureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feature not found"));
        feature.setParentId(request.getParentId());
        feature.setMenuOrder(request.getMenuOrder());
        feature.setTitle(request.getTitle());
        feature.setRouterLink(request.getRouterLink());
        feature.setIcon(request.getIcon());
        return featureRepository.save(feature);
    }

    @Override
    public FeatureEntity applyPermissions(FeaturePermissionRequest request) {
        FeatureEntity feature = featureRepository.findById(request.getFeatureId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (Long permissionId : request.getPermissionIds()) {
            PermissionEntity permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + permissionId));

            boolean alreadyAssigned = feature.getFeaturePermission().stream()
                    .anyMatch(ur -> ur.getPermission().equals(permission));

            if (!alreadyAssigned) {
                FeaturePermissionEntity featurePermission = new FeaturePermissionEntity();
                featurePermission.setFeature(feature);
                featurePermission.setPermission(permission);

                // Optional: set bidirectional relation
                feature.getFeaturePermission().add(featurePermission);
            }
        }

        return featureRepository.save(feature);
    }

    @Override
    public List<FeaturePermissionResponse> getPermissions(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<FeaturePermissionResponse> featurePermissions = roleRepository.findAll().stream()
                .flatMap(userRole -> userRole.getFeatures().stream())
                .map(feature -> new FeaturePermissionResponse(
                        feature.getId(),
                        feature.getTitle(),
                        feature.getPermissions().stream()
                                .map(perm -> new PermissionDto(perm.getId(), perm.getFunctionName(), perm.getFunctionOrder()))
                                .toList()
                ))
                .toList();

        return featurePermissions;
    }
}
