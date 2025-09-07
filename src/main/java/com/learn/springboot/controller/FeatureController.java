package com.learn.springboot.controller;

import com.learn.springboot.dto.feature.*;
import com.learn.springboot.service.FeatureService;
import com.learn.springboot.service.serviceImp.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
@RequiredArgsConstructor
public class FeatureController {
    private final FeatureService featureService;
    private final MenuService menuService;

    @PreAuthorize("hasAnyAuthority('FEATURE_VIEW')")
    @GetMapping
    public List<FeatureOutputResponse> getFeatures() {
        return featureService.findAll().stream().map(FeatureMapper::toDto).toList();
    }

    @PreAuthorize("hasAnyAuthority('FEATURE_CREATE')")
    @PostMapping
    public FeatureOutputResponse create(@RequestBody FeatureInputRequest body) {
        return FeatureMapper.toDto(featureService.save(body.toDto()));
    }

    @PreAuthorize("hasAnyAuthority('FEATURE_UPDATE')")
    @PostMapping("/{id}")
    public FeatureOutputResponse update(@PathVariable Long id, @RequestBody FeatureInputRequest body) {
        return FeatureMapper.toDto(featureService.update(id, body.toDto()));
    }

    @PreAuthorize("hasAnyAuthority('FEATURE_APPLY_PERMISSION')")
    @PostMapping("apply-permission")
    public FeatureOutputResponse applyPermission(@RequestBody FeaturePermissionRequest body) {
        return FeatureMapper.toDto(featureService.applyPermissions(body));
    }


    @GetMapping("/get-menu-item/{id}")
    public List<FeatureOutputResponse> getMenu(@PathVariable Long id) {
        return menuService.getMenuByUser(id);
    }

    @GetMapping("function-permission/{id}")
    public List<FeaturePermissionResponse> getFeature(@PathVariable Long id) {
        return featureService.getPermissions(id);
    }

}
