package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.domain.MemberRole;
import com.member.homework.domain.Role;
import com.member.homework.dto.request.LoginUserCommand;
import com.member.homework.repository.MemberRepository;
import com.member.homework.repository.MemberRoleRepository;
import com.member.homework.repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class AdminLoginServiceTest {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("관리자 권한이 있는 사용자는 로그인에 성공해야 한다.")
    void loginTest() {
        // given
        String id = "mb1";
        String password = "1234";

        createAdminMember(id, password);
        LoginUserCommand command = new LoginUserCommand("mb1", "1234");

        // when
        String result = adminLoginService.login(command);

        // then
        assertThat(result).isEqualTo("jwttoken");
    }

    private void createAdminMember(String id, String password) {
        MemberRole memberRole = MemberRole.of();
        memberRoleRepository.save(memberRole);

        Role role = Role.of("ADMIN");
        role.setMemberRole(memberRole);
        roleRepository.save(role);

        Member member = Member.of(id, passwordEncoder.encode(password));
        member.setMemberRole(memberRole);
        memberRepository.save(member);
    }
}