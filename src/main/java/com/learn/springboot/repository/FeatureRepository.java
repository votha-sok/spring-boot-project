package com.learn.springboot.repository;

import com.learn.springboot.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Integer> {
}
