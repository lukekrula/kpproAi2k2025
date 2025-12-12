package cz.uhk.kppro.security;

import cz.uhk.kppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // ✅ Register authentication provider BEFORE building the chain
        var authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

        var authenticationManager = authBuilder.build();
        http.authenticationManager(authenticationManager);

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/super/**").hasRole("SUPER_USER")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/css/**", "/images/**", "/data/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            var authorities = authentication.getAuthorities();
                            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin/administration");
                            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                                response.sendRedirect("/logged");
                            } else {
                                response.sendRedirect("/");
                            }
                        })
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()));

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) ->
                response.sendRedirect("/403");
    }
}
