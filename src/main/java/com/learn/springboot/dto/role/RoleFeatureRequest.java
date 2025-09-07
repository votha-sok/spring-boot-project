package com.learn.springboot.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleFeatureRequest {
    private Long roleId;
    private List<Long> featureIds;
}
