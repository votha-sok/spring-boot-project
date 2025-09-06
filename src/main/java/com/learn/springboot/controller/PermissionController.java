package com.learn.springboot.controller;

import com.learn.springboot.controller.request.FeaturePermissionUpdateRequest;
import com.learn.springboot.entity.PermissionEntity;
import com.learn.springboot.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/permissions") // 👈 base path
@RequiredArgsConstructor
public class PermissionController {

}
