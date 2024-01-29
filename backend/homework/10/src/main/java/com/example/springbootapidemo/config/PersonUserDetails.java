package com.example.springbootapidemo.config;

import com.example.springbootapidemo.model.Person;
import com.example.springbootapidemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * converting person to UserDetails format
 * Custom implementation of UserDetailsService for Spring Security.
 * Loads user details from the PersonService, including username, password, and authorities.
 */

@Component
public class PersonUserDetails implements UserDetailsService {
    private PersonService personService;
    @Autowired
    public PersonUserDetails(PersonService personService) {
        this.personService=personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getPersonUsername(username);
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(person == null){
            throw new UsernameNotFoundException("User details not found for user : " + username + ". Please register fist.");
        }else{
            personUserName = person.getUsername();
            personPassword = person.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(person.getRole()));
        }
        return new User(personUserName, personPassword, authorities);
    }
}
