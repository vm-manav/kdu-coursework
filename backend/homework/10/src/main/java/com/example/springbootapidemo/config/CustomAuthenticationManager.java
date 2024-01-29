package com.example.springbootapidemo.config;

import com.example.springbootapidemo.model.Person;
import com.example.springbootapidemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Custom authentication manager for Spring Security, providing authentication logic based on a PersonService and PasswordEncoder.
 * Validates user credentials, checks for user existence, and grants authorities based on the user's role.
 */


@Configuration
public class CustomAuthenticationManager implements AuthenticationProvider {
    private PersonService personService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public CustomAuthenticationManager(PersonService personService,PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.personService=personService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Person person = personService.getPersonUsername(username);

        if(person == null){
            throw new BadCredentialsException("No user registered with this details!");
        }else{
            if (passwordEncoder.matches(pwd, person.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(person.getRole()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
}
