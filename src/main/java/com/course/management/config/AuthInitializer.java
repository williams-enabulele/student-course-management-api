package com.course.management.config;

import com.course.management.domain.AppUser;
import com.course.management.domain.Role;
import com.course.management.service.interfaces.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class AuthInitializer {
    @Bean
    CommandLineRunner authCommandLineRunner(IUserService IUserService) {
        return args -> {
            IUserService.saveRole(new Role(null, "ROLE_USER"));
            IUserService.saveRole(new Role(null, "ROLE_MANAGER"));
            IUserService.saveRole(new Role(null, "ROLE_ADMIN"));
            IUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            IUserService.saveUser(new AppUser( "Gains", "0xwilliams", "password", new ArrayList<>()));
            IUserService.saveUser(new AppUser("Galant Rocks", "glory", "password", new ArrayList<>()));
            IUserService.saveUser(new AppUser("Jamal Gregs", "eternity", "password", new ArrayList<>()));

            IUserService.addRoleToUser("0xwilliams", "ROLE_USER");
            IUserService.addRoleToUser("0xwilliams", "ROLE_MANAGER");
            IUserService.addRoleToUser("0xwilliams", "ROLE_ADMIN");
            IUserService.addRoleToUser("0xwilliams", "ROLE_SUPER_ADMIN");
            IUserService.addRoleToUser("glory", "ROLE_USER");
            IUserService.addRoleToUser("eternity", "ROLE_USER");
        };
    }
}
