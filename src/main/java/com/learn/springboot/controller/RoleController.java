package com.learn.springboot.controller;
import com.learn.springboot.dto.role.RoleDto;
import com.learn.springboot.dto.role.RoleInputRequest;
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
        return roleService.findAll();
    }
    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) {
        return roleService.findById(id);
    }


    @PostMapping
    public RoleDto save(@RequestBody RoleInputRequest request) {
        return roleService.save(request.toRoleEntity());
    }

    @PostMapping("/{id}")
    public RoleDto update(@PathVariable Long id, @RequestBody RoleInputRequest role) {
        return roleService.update(id, role.toRoleEntity());
    }

}
