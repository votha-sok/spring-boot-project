package com.learn.springboot.dto.user;

import com.learn.springboot.entity.UserEntity;

public class UserInputRequest extends UserDto {
    String password;
    public UserEntity toDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(getUserName());
        userEntity.setId(getId());
        userEntity.setPhone(getPhone());
        userEntity.setEmail(getEmail());
        userEntity.setPassword(password);
        return userEntity;
    }
}
