package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.dto.request.LoginUserCommand;
import com.member.homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginUserCommand command) {
        Member findMember = memberRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        checkPassword(command.password(), findMember.getPassword());
        checkIsAdmin(findMember);

        return "jwttoken";
    }

    private static void checkIsAdmin(Member findMember) {
        boolean isAdmin = findMember.getMemberRoles()
                .stream()
                .anyMatch(role -> role.getRole().getRoleName().equals("ADMIN"));

        if (!isAdmin) {
            throw new IllegalArgumentException("관리자 권한이 없습니다. 관리자 페이지에 진입할 수 없습니다.");
        }
    }

    private void checkPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 다릅니다.");
        }
    }
}
