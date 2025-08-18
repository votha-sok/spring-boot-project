package com.learn.springboot.service.serviceImp;

import com.learn.springboot.controller.request.AccountCreateRequest;
import com.learn.springboot.entity.AccountEntity;
import com.learn.springboot.repository.AccountRepository;
import com.learn.springboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountEntity findByAccountName(String accountName) {
        return null;
    }

    @Override
    public AccountEntity save(AccountEntity account) {
        return accountRepository.save(account);
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountEntity findById(Long id) {
        final var accountEntity = accountRepository.findById(id);
        return accountEntity.orElse(null);
    }

    @Override
    @Transactional
    public AccountEntity updateAccount(Long id, AccountCreateRequest request) throws AccountNotFoundException {
        final var account = findById(id);
        if (account == null) {
            throw new AccountNotFoundException("Account Not found");
        }
        account.updateAccount(request);
        return accountRepository.save(account);
    }
}
