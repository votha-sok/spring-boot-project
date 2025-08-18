package com.learn.springboot.service;

import com.learn.springboot.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity save(UserEntity userEntity);
    List<UserEntity> listAll(Pageable pageable);
}
