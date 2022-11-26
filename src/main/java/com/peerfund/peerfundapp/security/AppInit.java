package com.peerfund.peerfundapp.security;

import com.peerfund.peerfundapp.entities.Enums.RoleType;
import com.peerfund.peerfundapp.entities.Role;
import com.peerfund.peerfundapp.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public AppInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(1L, RoleType.GROUP_ADMIN);
        roleRepository.save(adminRole);

        Role userRole = new Role(2L,RoleType.MEMBER);
        roleRepository.save(userRole);
    }
}
