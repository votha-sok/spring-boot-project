package com.learn.springboot.controller;

import com.learn.springboot.dto.permission.PermissionDto;
import com.learn.springboot.dto.permission.PermissionInputRequest;
import com.learn.springboot.dto.permission.PermissionMapper;
import com.learn.springboot.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions") // 👈 base path
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    public List<PermissionDto> getPermissions() {
        return permissionService.findAll().stream().map(PermissionMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public PermissionDto getPermission(@PathVariable Long id) {
        return  PermissionMapper.toDto(permissionService.findById(id));
    }

    @PostMapping
    public PermissionDto save(@Valid @RequestBody PermissionInputRequest request) {
        return PermissionMapper.toDto(permissionService.save(request.toDto()));
    }

    @PutMapping("/{id}") // 👈 use PUT for full update
    public PermissionDto update(
            @PathVariable Long id,
            @Valid @RequestBody PermissionInputRequest request) {
        return PermissionMapper.toDto(permissionService.update(id, request.toDto()));
    }
}
