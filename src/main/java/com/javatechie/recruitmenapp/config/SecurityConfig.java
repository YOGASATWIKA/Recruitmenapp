package com.javatechie.recruitmenapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/vacancy").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/vacancy").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/vacancy").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/vacancy/active").hasAnyRole("ADMIN", "CANDIDATE")
                        .requestMatchers(HttpMethod.GET, "/vacancy").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails candidate = User.builder()
                .username("candidate")
                .password(passwordEncoder.encode("candidate123"))
                .roles("CANDIDATE")
                .build();

        return new InMemoryUserDetailsManager(admin, candidate);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
