package cz.uhk.kppro.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DevToolsConfig {

    @Bean
    CommandLineRunner passwordGenerator() {
        return args -> {
            System.out.println(new BCryptPasswordEncoder().encode("admin123"));
        };
    }
}
