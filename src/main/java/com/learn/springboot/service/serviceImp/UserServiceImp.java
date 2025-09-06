package com.learn.springboot.service.serviceImp;

import com.learn.springboot.dto.user.UserMapper;
import com.learn.springboot.dto.user.UserOutputResponse;
import com.learn.springboot.dto.user.UserRoleInputRequest;
import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.entity.UserRoleEntity;
import com.learn.springboot.exception.ResourceNotFoundException;
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
    public UserOutputResponse save(UserEntity userEntity) {
        return UserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public UserOutputResponse update(Long id, UserEntity request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user.setUserName(request.getUserName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserEntity addRolesToUser(UserRoleInputRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (Long roleId : request.getRoleIds()) {
            RoleEntity role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));

            boolean alreadyAssigned = user.getUserRoles().stream()
                    .anyMatch(ur -> ur.getRole().equals(role));

            if (!alreadyAssigned) {
                UserRoleEntity userRole = new UserRoleEntity();
                userRole.setUser(user);
                userRole.setRole(role);

                // Optional: set bidirectional relation
                user.getUserRoles().add(userRole);
            }
        }

        return userRepository.save(user);
    }


    @Override
    public List<UserOutputResponse> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserOutputResponse findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
