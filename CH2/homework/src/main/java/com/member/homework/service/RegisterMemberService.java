package com.member.homework.service;

import com.member.homework.dto.request.RegisterMemberCommand;
import com.member.homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterMemberService {

    private final MemberRepository memberRepository;

    public Long register(RegisterMemberCommand command) {
        return 999L;
    }
}
