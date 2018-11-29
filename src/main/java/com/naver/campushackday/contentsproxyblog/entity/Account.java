package com.naver.campushackday.contentsproxyblog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
