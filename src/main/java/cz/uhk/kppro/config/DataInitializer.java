package cz.uhk.kppro.config;

import cz.uhk.kppro.model.Role;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.RoleRepository;
import cz.uhk.kppro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // --- ROLES -------------------------------------------------------

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ADMIN")));

            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(new Role("USER")));

            // --- ADMIN USER --------------------------------------------------

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole(adminRole);
                admin.setEnabled(true);

                userRepository.save(admin);

                System.out.println("✔ Default admin user created: admin / admin");
            }

            // --- NORMAL USER -------------------------------------------------

            if (userRepository.findByUsername("user").isEmpty()) {

                User user = new User();
                user.setUsername("user");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(userRole);
                user.setEnabled(true);

                userRepository.save(user);

                System.out.println("✔ Default user created: user / user123");
            }
        };
    }
}
