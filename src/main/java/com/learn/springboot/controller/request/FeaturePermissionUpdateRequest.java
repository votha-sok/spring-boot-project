package com.learn.springboot.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class FeaturePermissionUpdateRequest {
    private Long id;
    private List<Integer> permissions; // permission IDs
}
