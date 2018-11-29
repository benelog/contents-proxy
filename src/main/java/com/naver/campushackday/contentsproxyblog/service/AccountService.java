package com.naver.campushackday.contentsproxyblog.service;

import com.naver.campushackday.contentsproxyblog.entity.Account;
import com.naver.campushackday.contentsproxyblog.persistence.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account register(Account account) {
        return repository.save(Account.giveUserRole(account));
    }
}
