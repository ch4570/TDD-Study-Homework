package com.member.homework.service;

import com.member.homework.domain.Member;
import com.member.homework.dto.request.GrantRoleCommand;
import com.member.homework.repository.MemberRepository;
import com.member.homework.repository.MemberRoleRepository;
import com.member.homework.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GrantRoleService {

    private final RoleRepository roleRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final MemberRepository memberRepository;

    public void grantRoleToMember(Long memberId, List<GrantRoleCommand> roleList) {

    }
}
