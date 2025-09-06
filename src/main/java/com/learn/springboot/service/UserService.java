package com.learn.springboot.service;

import com.learn.springboot.dto.user.UserDto;
import com.learn.springboot.dto.user.UserOutputResponse;
import com.learn.springboot.dto.user.UserRoleInputRequest;
import com.learn.springboot.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserOutputResponse save(UserEntity userEntity);
    UserOutputResponse update(Long id, UserEntity userEntity);
    UserEntity addRolesToUser(UserRoleInputRequest request);
    List<UserOutputResponse> findAll();
    UserOutputResponse findById(Long id);

}
