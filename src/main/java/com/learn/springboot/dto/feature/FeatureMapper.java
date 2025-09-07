package com.learn.springboot.dto.feature;

import com.learn.springboot.entity.FeatureEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;

public class FeatureMapper {
    public static FeatureOutputResponse toDto(FeatureEntity feature) {
        return new FeatureOutputResponse(feature.getId(), feature.getTitle(), feature.getIcon(), feature.getMenuOrder(), feature.getRouterLink(), null);
    }
}
