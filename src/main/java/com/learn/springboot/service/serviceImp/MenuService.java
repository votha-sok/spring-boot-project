package com.learn.springboot.service.serviceImp;

import com.learn.springboot.dto.MenuItemDTO;
import com.learn.springboot.dto.feature.FeatureOutputResponse;
import com.learn.springboot.entity.FeatureEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.repository.FeatureRepository;
import com.learn.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final UserRepository userRepository;
    private final FeatureRepository featureRepository; // for all features

    public List<FeatureOutputResponse> getMenuByUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<FeatureEntity> userFeatures;

        if (user.getIsSuperAdmin()) {
            // Return all top-level features for super admin
            userFeatures = new HashSet<>(featureRepository.findAll());
        }
        else {
            // Collect all features from user roles
            userFeatures = user.getRoles().stream()
                    .flatMap(role -> role.getFeatures().stream())
                    .collect(Collectors.toSet());
        }

        // Only top-level features
        return userFeatures.stream()
                .filter(f -> f.getParentId() == null)
                .map(this::toMenuItem)
                .collect(Collectors.toList());
    }

    private FeatureOutputResponse toMenuItem(FeatureEntity feature) {
        FeatureOutputResponse item = new FeatureOutputResponse();
        item.setTitle(feature.getTitle());
        item.setIcon(feature.getIcon());

        // If you only use one routerLink per feature, take the first
        if (feature.getRouterLink() != null && !feature.getRouterLink().isEmpty()) {
            item.setRouterLink(feature.getRouterLink());
        }

        // Map children recursively
        List<FeatureEntity> children = featureRepository.findAllByParentId((feature.getId()));
        if (children != null) {
            List<FeatureOutputResponse> items = children.stream()
                    .map(this::toMenuItem)
                    .collect(Collectors.toList());
            item.setItems(items);
        }
        return item;
    }

}
