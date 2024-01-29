package com.example.springbootapidemo.service;

import com.example.springbootapidemo.dao.PersonDAO;
import com.example.springbootapidemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class implementing CommandLineRunner to add startup data for Person entities in a Spring Boot application.
 * Utilizes a PersonDAO to add persons with predefined roles (Admin and User) and encoded passwords during application startup.
 */

@Service
public class StartUpDataAddition implements CommandLineRunner {
    private PersonDAO personDAO;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public StartUpDataAddition(PersonDAO personDAO,PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.personDAO=personDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        personDAO.addPerson(new Person("Admin", "admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
        personDAO.addPerson(new Person("User", "user", passwordEncoder.encode("user"), "ROLE_BASIC"));
    }
}
