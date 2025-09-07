package com.learn.springboot.dto.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturePermissionRequest {
    private Long featureId;
    private List<Long> permissionIds;
}
