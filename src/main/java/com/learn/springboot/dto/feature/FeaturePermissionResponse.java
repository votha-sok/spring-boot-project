package com.learn.springboot.dto.feature;

import com.learn.springboot.dto.permission.PermissionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeaturePermissionResponse {
    private Long id;
    private String featureName;
    private List<PermissionDto> permissions;
}
