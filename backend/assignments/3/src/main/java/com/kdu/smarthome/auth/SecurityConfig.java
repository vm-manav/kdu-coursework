package com.kdu.smarthome.auth;

import com.kdu.smarthome.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Configuration class responsible for defining security settings in the Smart Home application.
 * Configures user authentication, authorization filters, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final UsersService usersService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Constructs a SecurityConfig with the provided UsersService and JwtAuthorizationFilter.
     *
     * @param usersService            The UsersService for user-related operations.
     * @param jwtAuthorizationFilter The JwtAuthorizationFilter for JWT token handling.
     */
    @Autowired
    public SecurityConfig(UsersService usersService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.usersService=usersService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    /**
     * Defines the AuthenticationManager bean for user authentication.
     * Configures the userDetailsService and returns the configured AuthenticationManager.
     *
     * @param http            The HttpSecurity configuration.
     * @param passwordEncoder The PasswordEncoder for password-related operations.
     * @return The configured AuthenticationManager.
     * @throws Exception If an error occurs during the configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(usersService);
        return authenticationManagerBuilder.build();
    }
    /**
     * Defines the SecurityFilterChain bean for configuring security settings.
     * Configures CSRF, authorization rules, session management, and JWT filter.
     *
     * @param http The HttpSecurity configuration.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/house/**").authenticated()
                .requestMatchers("/api/v1/house/{houseId}/add-user").authenticated()
                .anyRequest().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    /**
     * Defines the NoOpPasswordEncoder bean for password encoding.
     * Configures the NoOpPasswordEncoder for handling passwords without encoding.
     *
     * @return The configured NoOpPasswordEncoder.
     */

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
