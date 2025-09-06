package com.learn.springboot.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class FeatureCreateRequest {
    private String title;
    private Integer parentId;
    private List<Integer> permissions; // permission IDs
}
