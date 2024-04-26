package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.dto.request.RegisterMemberCommand;
import com.member.homework.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class RegisterMemberServiceTest {

    @Autowired
    private RegisterMemberService registerMemberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("관리자는 회원을 생성할 수 있어야 한다.")
    void registerTest() {
        // given
        String id = "mb1";
        String name = "궁햄";
        RegisterMemberCommand command = new RegisterMemberCommand(id, "1234", name);

        // when
        Long memberId = registerMemberService.register(command);
        Member member = memberRepository.findById(id)
                .orElseThrow();

        // then
        assertThat(member.getMemberId()).isEqualTo(memberId);
        assertThat(member.getName()).isEqualTo(name);
    }

}