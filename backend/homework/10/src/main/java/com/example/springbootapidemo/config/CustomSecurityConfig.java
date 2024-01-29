package com.example.springbootapidemo.config;

import com.example.springbootapidemo.filter.TokenGeneratorFilter;
import com.example.springbootapidemo.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * Configuration class for custom security settings in a Spring Boot application.
 * Defines filters for token generation and validation, sets up URL permissions, and configures password encoding.
 */


@Configuration
public class CustomSecurityConfig {

    @Bean
    @SuppressWarnings("java:S5738")
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.
                addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/person/login").permitAll()
                        .requestMatchers("/username/{userName}","/users").hasAnyRole("ADMIN","BASIC")
                        .requestMatchers("/user").hasRole("ADMIN")
                        .anyRequest().authenticated()).csrf().disable();
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
