package com.learn.springboot.dto.feature;

import com.learn.springboot.entity.FeatureEntity;
import lombok.Data;

@Data
public class FeatureInputRequest extends FeatureDto {
    private Long parentId;
    public FeatureEntity toDto() {
        FeatureEntity featureEntity = new FeatureEntity();
        featureEntity.setRouterLink(getRouterLink());
        featureEntity.setMenuOrder(getMenuOrder());
        featureEntity.setTitle(getTitle());
        featureEntity.setIcon(getIcon());
        featureEntity.setParentId(parentId);
        return featureEntity;
    }
}
