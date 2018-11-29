package com.naver.campushackday.contentsproxyblog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    public Account(String userId, String password){
        this.userId = userId;
        this.password = encryptPassword(password);
        this.userRole = UserRole.USER;
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
