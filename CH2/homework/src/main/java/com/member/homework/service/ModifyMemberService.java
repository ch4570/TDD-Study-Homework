package com.member.homework.service;

import com.member.homework.dto.request.ModifyMemberCommand;
import com.member.homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyMemberService {

    private final MemberRepository memberRepository;

    public void modifyMember(ModifyMemberCommand command) {

    }

}
