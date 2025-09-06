package com.learn.springboot.controller;

import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.service.RoleService;
import com.learn.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register() {


        RoleEntity roleUser = new RoleEntity();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);
        RoleEntity roleAdmin = new RoleEntity();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.save(roleAdmin);

        UserEntity u1 = new UserEntity();
        u1.setUserName("john");
        u1.setEmail("john@example.com");
        u1.setPassword(encoder.encode("1234"));
//        u1.setRoles(Set.of(roleUser, roleAdmin));
        final var result = userService.save(u1);
        return null;
    }

//    @PostMapping("/{userId}/roles/{roleId}")
//    public UserDto assignRole(@PathVariable Long userId, @PathVariable Long roleId) {
//        UserEntity updatedUser = userService.addRoleToUser(userId, roleId);
//        return UserMapper.toDto(updatedUser);
//    }
//
//    @GetMapping
//    public List<UserDto> getAllUsers() {
//        return userService.f()
//                .stream()
//                .map(UserMapper::toDto)
//                .toList();
//    }
}
