package com.learn.springboot.controller.request;

import com.learn.springboot.entity.AccountEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class AccountCreateRequest {

    @NotBlank(message = "Account name can not be blank.")
    String accountName;
    @NotBlank
    String accountNo;
    @NotNull
    String accountType;

    public AccountEntity toAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName(accountName);
        accountEntity.setAccountNo(accountNo);
        accountEntity.setAccountType(accountType);
        return accountEntity;
    }
}
