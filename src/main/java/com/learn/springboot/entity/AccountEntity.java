package com.learn.springboot.entity;


import com.learn.springboot.controller.request.AccountCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "account_name")
    private String accountName;

    @Setter
    @Column(name = "account_no")
    private String accountNo;

    @Setter
    @Column(name = "account_type")
    private String accountType;

    public AccountEntity updateAccount(AccountCreateRequest  body) {
        this.accountNo = body.getAccountNo();
        this.accountType = body.getAccountType();
        this.accountName = body.getAccountName();
        return this;
    }

}
