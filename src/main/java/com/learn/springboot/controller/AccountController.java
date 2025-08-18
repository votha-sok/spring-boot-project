package com.learn.springboot.controller;

import com.learn.springboot.controller.request.AccountCreateRequest;
import com.learn.springboot.entity.AccountEntity;
import com.learn.springboot.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<AccountEntity> save(@RequestBody AccountCreateRequest body) {
        final var request = body.toAccountEntity();
        final var result = accountRepository.save(request);
        return ResponseEntity.ok().body(result);
    }
    @PatchMapping
    public ResponseEntity<AccountEntity> update(@RequestBody AccountCreateRequest body) {
        final var request = body.toAccountEntity();
        final var result = accountRepository.save(request);
        return ResponseEntity.ok().body(result);
    }
}
