package com.member.homework.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public record GrantRoleCommand(String role) {
}
