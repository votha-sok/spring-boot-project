package com.learn.springboot.service.serviceImp;

import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserNameOrEmail(name, name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetail(user);
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUserName())
//                .password(user.getPassword())
//                .authorities(user.getRoles().stream()
//                        .map(RoleEntity::getName)
//                        .toArray(String[]::new))
//                .build();
    }
}
