package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.domain.MemberRole;
import com.member.homework.repository.MemberRepository;
import com.member.homework.repository.MemberRoleRepository;
import com.member.homework.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class RemoveMemberServiceTest {

    @Autowired
    private RemoveMemberService removeMemberService;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("관리자는 회원을 삭제할 수 있어야 한다.")
    void removeMemberTest() {
        // given
        Long memberId = testUtil.createMember("mb1", "1234", "ADMIN", "궁햄112");

        // when
        removeMemberService.removeMember(memberId);
        Optional<Member> findMember = memberRepository.findById("mb1");

        List<MemberRole> memberRoleList = memberRoleRepository.findAll();

        // then
        assertThat(findMember.isEmpty()).isTrue();
        assertThat(memberRoleList).isEmpty();

    }

}