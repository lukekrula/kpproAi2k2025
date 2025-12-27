package cz.uhk.kppro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KpproApplication {

    public static void main(String[] args) {
        SpringApplication.run(KpproApplication.class, args);
    }


    /*@Bean
    CommandLineRunner generateHash() { return args -> { System.out.println("Pass" +  new BCryptPasswordEncoder().encode("admin123")); }; }
     */

}

