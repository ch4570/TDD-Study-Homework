package com.member.homework.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", columnDefinition = "INTEGER")
    private Long memberId;

    @Column(name = "id", columnDefinition = "VARCHAR(30)")
    private String id;

    @Column(name = "password", columnDefinition = "VARCHAR(200)")
    private String password;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MemberRole> memberRoles = new ArrayList<>();

    @Builder
    private Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static Member of(String id, String password) {
        return Member.builder()
                .id(id)
                .password(password)
                .build();
    }
}
