package com.learn.springboot.service.serviceImp;

import com.learn.springboot.dto.role.RoleFeatureRequest;
import com.learn.springboot.entity.*;
import com.learn.springboot.exception.ResourceNotFoundException;
import com.learn.springboot.repository.FeatureRepository;
import com.learn.springboot.repository.PermissionRepository;
import com.learn.springboot.repository.RoleRepository;
import com.learn.springboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    private final FeatureRepository featureRepository;

    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity save(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity update(Long id, RoleEntity role) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
        roleEntity.setName(role.getName());
        roleEntity.setDescription(role.getDescription());
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity applyRoleFeature(RoleFeatureRequest request) {
        RoleEntity roleEntity = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (Long featureId : request.getFeatureIds()) {
            FeatureEntity feature = featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));

//            boolean alreadyAssigned = feature.getRoleFeature().stream()
//                    .anyMatch(ur -> ur.getFeature().equals(feature));
//
//            if (!alreadyAssigned) {
                RoleFeatureEntity roleFeature = new RoleFeatureEntity();
                roleFeature.setFeature(feature);
                roleFeature.setRole(roleEntity);

                // Optional: set bidirectional relation
                roleEntity.getRoleFeature().add(roleFeature);
//            }
        }

        return roleRepository.save(roleEntity);
    }

}
