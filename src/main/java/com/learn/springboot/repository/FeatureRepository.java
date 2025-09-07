package com.learn.springboot.repository;

import com.learn.springboot.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

    List<FeatureEntity> findAllByParentId(Long parentId);
}
