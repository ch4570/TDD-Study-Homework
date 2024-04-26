package com.member.homework.service;

import com.member.homework.dto.request.LoginUserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class AdminLoginServiceTest {

    @Autowired
    private AdminLoginService adminLoginService;

    @Test
    @DisplayName("관리자 권한이 있는 사용자는 로그인에 성공해야 한다.")
    void loginTest() {
        // given
        LoginUserCommand command = new LoginUserCommand("mb1", "1234");

        // when
        String result = adminLoginService.login(command);

        // then
        assertThat(result).isEqualTo("jwttoken");
    }
}