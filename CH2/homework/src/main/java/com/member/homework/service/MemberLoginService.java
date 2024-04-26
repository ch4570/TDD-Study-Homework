package com.member.homework.service;

import com.member.homework.dto.request.LoginMemberCommand;
import com.member.homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginMemberCommand command) {
        return "";
    }
}
