package com.learn.springboot.service.serviceImp;

import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.repository.UserRepository;
import com.learn.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> listAll(Pageable pageable) {
        return List.of();
    }
}
