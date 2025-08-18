package com.learn.springboot.service;

import com.learn.springboot.controller.request.AccountCreateRequest;
import com.learn.springboot.entity.AccountEntity;
import com.learn.springboot.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public interface AccountService {
    AccountEntity findByAccountName(String accountName);
    AccountEntity save(AccountEntity accountEntity);
    List<AccountEntity> findAll();
    AccountEntity findById(Long id);
    AccountEntity updateAccount(Long id, AccountCreateRequest request) throws AccountNotFoundException;
}
