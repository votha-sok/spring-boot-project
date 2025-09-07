package com.learn.springboot.controller;
import com.learn.springboot.dto.role.RoleDto;
import com.learn.springboot.dto.role.RoleFeatureRequest;
import com.learn.springboot.dto.role.RoleInputRequest;
import com.learn.springboot.dto.role.RoleMapper;
import com.learn.springboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<RoleDto> findAll() {
        return roleService.findAll().stream().map(RoleMapper::toDto).toList();
    }
    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) {
        return RoleMapper.toDto(roleService.findById(id));
    }


    @PostMapping
    public RoleDto save(@RequestBody RoleInputRequest request) {
        return RoleMapper.toDto(roleService.save(request.toRoleEntity()));
    }

    @PostMapping("/{id}")
    public RoleDto update(@PathVariable Long id, @RequestBody RoleInputRequest role) {
        return RoleMapper.toDto(roleService.update(id, role.toRoleEntity()));
    }

    @PostMapping("/apply-feature")
    public RoleDto update(@RequestBody RoleFeatureRequest request) {
        return RoleMapper.toDto(roleService.applyRoleFeature(request));
    }


}
