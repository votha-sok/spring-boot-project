package com.learn.springboot.dto.user;

import com.learn.springboot.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserOutputResponse toDto(UserEntity user) {
        Set<UserRoleDto> roleDtos = user.getUserRoles().stream()
                .map(ur -> new UserRoleDto(
                        ur.getRole().getId(),
                        ur.getRole().getName()
                ))
                .collect(Collectors.toSet());
        return new UserOutputResponse(user.getId(), user.getUserName(), user.getEmail(), user.getPhone(), roleDtos);
    }
}
