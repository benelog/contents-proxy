package com.naver.campushackday.contentsproxyblog.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserRoleTest {

    @Test
    public void GET_ROLE_BY_NAME_TEST() {
        assertThat(UserRole.getRoleByName("ROLE_ADMIN")).isEqualTo(UserRole.ADMIN);
    }
}