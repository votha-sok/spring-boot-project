package com.learn.springboot.controller;

import com.learn.springboot.dto.role.RoleInputRequest;
import com.learn.springboot.dto.user.*;
import com.learn.springboot.entity.RoleEntity;
import com.learn.springboot.entity.UserEntity;
import com.learn.springboot.service.RoleService;
import com.learn.springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    @PostMapping("/register")
    public UserOutputResponse register() {


//        RoleEntity roleUser = new RoleEntity();
//        roleUser.setName("ROLE_USER");
//        roleService.save(roleUser);
//        RoleEntity roleAdmin = new RoleEntity();
//        roleAdmin.setName("ROLE_ADMIN");
//        roleService.save(roleAdmin);

        UserEntity u1 = new UserEntity();
        u1.setUserName("john");
        u1.setEmail("john@example.com");
        u1.setPassword(encoder.encode("1234"));
//        u1.setRoles(Set.of(roleUser, roleAdmin));

        return userService.save(u1);
    }

    @PostMapping
    public UserOutputResponse create(@RequestBody UserInputRequest request) {
        return userService.save(request.toDto());
    }

    @PostMapping("/{id}")
    public UserOutputResponse update(@PathVariable Long id, @RequestBody UserInputRequest request) {
        return userService.update(id, request.toDto());
    }

    @PostMapping("/apply-role")
    public UserOutputResponse assignRole(@RequestBody @Valid UserRoleInputRequest request) {
        return UserMapper.toDto(userService.addRolesToUser(request));
    }

    @GetMapping
    public List<UserOutputResponse> finAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserOutputResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
