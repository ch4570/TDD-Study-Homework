package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.repository.MemberRepository;
import com.member.homework.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RevertPasswordServiceTest {

    @Autowired
    private RevertPasswordService revertPasswordService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestUtil testUtil;

    @Test
    @DisplayName("관리자는 사용자의 비밀번호를 초기화 할 수 있다.")
    void passwordRevertTest() {
        // given
        Long memberId = testUtil.createMember("mb1", "1234", "ADMIN", "궁햄112");

        // when
        String revertedPassword = revertPasswordService.revertPassword(memberId);
        Member findMember = memberRepository.findById(memberId).orElseThrow();

        // then
        assertThat(testUtil.matches(revertedPassword, findMember.getPassword())).isTrue();


    }

}