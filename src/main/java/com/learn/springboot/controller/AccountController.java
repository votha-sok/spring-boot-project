package com.learn.springboot.controller;

import com.learn.springboot.controller.request.AccountCreateRequest;
import com.learn.springboot.entity.AccountEntity;
import com.learn.springboot.repository.AccountRepository;
import com.learn.springboot.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountEntity> save(@RequestBody AccountCreateRequest body) {
        final var request = body.toAccountEntity();
        final var result = accountService.save(request);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountEntity> update(@PathVariable Long id, @RequestBody @Valid AccountCreateRequest body) throws AccountNotFoundException {
        final var accountEntity = accountService.updateAccount(id, body);
        if (accountEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(accountEntity);
    }

    @GetMapping
    public List<AccountEntity> getAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountEntity findById(@PathVariable Long id) {
        return accountService.findById(id);
    }
}
