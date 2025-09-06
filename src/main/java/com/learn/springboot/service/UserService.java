package com.learn.springboot.service;

import com.learn.springboot.dto.user.UserDto;
import com.learn.springboot.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto save(UserEntity userEntity);
    UserDto addRolesToUser(Long userId,  List<Long> roleIds);
    List<UserDto> findAll();
}
