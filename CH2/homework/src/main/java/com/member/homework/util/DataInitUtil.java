package com.member.homework.util;

import com.member.homework.domain.Role;
import com.member.homework.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitUtil {

    private final RoleRepository repository;


    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        repository.saveAll(List.of(
                Role.of("ADMIN"),
                Role.of("USER")
        ));
    }
}
