package com.naver.campushackday.contentsproxyblog.persistence;

import com.naver.campushackday.contentsproxyblog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
