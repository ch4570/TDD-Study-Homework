package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.domain.MemberRole;
import com.member.homework.domain.Role;
import com.member.homework.dto.request.LoginMemberCommand;
import com.member.homework.repository.MemberRepository;
import com.member.homework.repository.MemberRoleRepository;
import com.member.homework.repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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

        createMember(id, password, "ADMIN");
        LoginMemberCommand command = new LoginMemberCommand("mb1", "1234");

        // when
        String result = adminLoginService.login(command);

        // then
        assertThat(result).isEqualTo("jwttoken");
    }

    @Test
    @DisplayName("관리자 권한이 없는 사용자는 로그인에 실패해야 한다.")
    void isNotAdminMemberTest() {
        // given
        String id = "mb1";
        String password = "1234";

        createMember(id, password, "MEMBER");
        LoginMemberCommand command = new LoginMemberCommand(id, password);

        // when -> then
        assertThatThrownBy(() -> adminLoginService.login(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("관리자 권한이 없습니다. 관리자 페이지에 진입할 수 없습니다.");
    }

    @Test
    @DisplayName("회원은 존재하지만 아이디와 비밀번호가 다른 경우 로그인이 실패한다.")
    void isNotAuthorizedMemberTest() {
        // given
        String id = "mb1";

        createMember(id, "1234", "MEMBER");
        LoginMemberCommand command = new LoginMemberCommand(id, "1233");

        // when -> then
        assertThatThrownBy(() -> adminLoginService.login(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디 또는 비밀번호가 다릅니다.");
    }

    @Test
    @DisplayName("로그인 시도한 아이디가 존재하지 않는 아이디라면 로그인이 실패한다.")
    void isNotExistsMemberTest() {
        // given
        String password = "1234";

        createMember("mb1", password, "MEMBER");
        LoginMemberCommand command = new LoginMemberCommand("궁햄", "1234");

        // when -> then
        assertThatThrownBy(() -> adminLoginService.login(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }

    private void createMember(String id, String password, String roleName) {
        MemberRole memberRole = MemberRole.of();
        memberRoleRepository.save(memberRole);

        Role role = Role.of(roleName);
        role.setMemberRole(memberRole);
        roleRepository.save(role);

        Member member = Member.of(id, passwordEncoder.encode(password));
        member.setMemberRole(memberRole);
        memberRepository.save(member);
    }
}