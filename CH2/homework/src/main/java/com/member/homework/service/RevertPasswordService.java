package com.member.homework.service;

import com.member.homework.repository.MemberRepository;
import com.member.homework.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RevertPasswordService {

    private final MemberRepository memberRepository;
    private final PasswordUtil passwordUtil;

    public String revertPassword(Long userId) {
        return "";
    }
}
