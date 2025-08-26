package com.learn.springboot.controller;


import com.learn.springboot.configuration.annotation.AuditFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {


    @AuditFilter
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(1000); // simulate processing
        return "Hello World!";
    }
}
