package com.learn.springboot.service.serviceImp;

import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUserNameOrEmail(username,username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getUserRoles().forEach(userRole -> {
            userRole.getRole().getFeatures().forEach(feature -> {
                feature.getPermissions().forEach(permission -> {
                    String authority = feature.getTitle().toUpperCase()
                            + "_" + permission.getFunctionName().toUpperCase();
                    authorities.add(new SimpleGrantedAuthority(authority));
                });
            });
        });
        return new CustomUserDetail(user, authorities);
    }
}
