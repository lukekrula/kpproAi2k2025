package cz.uhk.kppro;

import cz.uhk.kppro.model.*;
import cz.uhk.kppro.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class KpproApplication {
    public static void main(String[] args) {
        SpringApplication.run(KpproApplication.class, args);
    }
}


