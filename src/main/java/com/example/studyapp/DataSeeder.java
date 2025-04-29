package com.example.studyapp;

import com.example.studyapp.entities.Profile;
import com.example.studyapp.entities.User;
import com.example.studyapp.enums.Role;
import com.example.studyapp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;

import java.util.Optional;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String anonUsername = "anonimo";

        Optional<User> existingUser = userRepository.findByUsername(anonUsername);
        if (existingUser.isEmpty()) {
            User user = new User();
            user.setUsername(anonUsername);
            user.setPassword(passwordEncoder.encode("12345678")); // Codificar la contraseña
            user.setEmail("anonimo@anonimo.com");
            user.setRole(Role.ROLE_USER);

            Profile profile = new Profile();
            profile.setName("Anon");
            profile.setLastname("User");
            profile.setUser(user);
            user.setProfile(profile);

            userRepository.save(user);

            System.out.println("Usuario 'anonimo' creado.");
        } else {
            System.out.println("Usuario 'anonimo' ya existe. No se hace nada.");
        }
    }
}