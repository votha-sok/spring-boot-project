package com.learn.springboot.service.serviceImp;

import com.learn.springboot.dto.user.UserDto;
import com.learn.springboot.dto.user.UserMapper;
import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.entity.UserRoleEntity;
import com.learn.springboot.repository.RoleRepository;
import com.learn.springboot.repository.UserRepository;
import com.learn.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDto save(UserEntity userEntity) {
        return UserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDto addRolesToUser(Long userId, List<Long> roleIds) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (Long roleId : roleIds) {
            RoleEntity role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));

            // check if already assigned
            boolean alreadyAssigned = user.getUserRoles().stream()
                    .anyMatch(ur -> ur.getRole().equals(role));

            if (!alreadyAssigned) {
                UserRoleEntity userRole = new UserRoleEntity();
                userRole.setUser(user);
                userRole.setRole(role);
                user.getUserRoles().add(userRole);
            }
        }

        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> findAll() {
        return  userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
