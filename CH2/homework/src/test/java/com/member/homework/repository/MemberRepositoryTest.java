package com.member.homework.repository;

import com.member.homework.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("가입된 회원을 전체 조회할 수 있어야 한다.")
    void findAllTest() {
        // given
        memberRepository.saveAll(
                List.of(
                   Member.of("member1", "1234"),
                   Member.of("member2", "5678"),
                   Member.of("member3", "1111")
                ));

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertThat(memberList)
                .extracting("id", "password")
                .containsExactlyInAnyOrder(
                        tuple("member1", "1234"),
                        tuple("member2", "5678"),
                        tuple("member3", "1111")
                );
    }

    @Test
    @DisplayName("회원의 고유 ID로 회원을 조회할 수 있어야 한다.")
    void findByIdTest() {
        // given
        String id = "javajunsuk123";
        Member member = Member.of(id, "devcamp");
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(id)
                .orElseThrow();

        // then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원의 ID로 회원 존재 여부를 알 수 있어야 한다.")
    void existsMemberById() {
        // given
        memberRepository.save(Member.of("mb1", "1234"));

        // when
        boolean successResult = memberRepository.existsMemberById("mb1");
        boolean failureResult = memberRepository.existsMemberById("mb2");

        // then
        assertThat(successResult).isTrue();
        assertThat(failureResult).isFalse();
    }

    @Test
    @DisplayName("회원의 고유 ID(PK)로 회원을 삭제할 수 있어야 한다.")
    void deleteById() {
        // given
        Member member = memberRepository.save(Member.of("mb1", "1234"));

        // when
        memberRepository.deleteById(member.getMemberId());

        // then
        assertThatThrownBy(() -> memberRepository.findById(member.getMemberId()).orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }



}