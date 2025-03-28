package com.example.studyapp.services;

import com.example.studyapp.dtos.UserDto;
import com.example.studyapp.entities.Profile;
import com.example.studyapp.entities.User;
import com.example.studyapp.enums.Role;
import com.example.studyapp.repositories.ProfileRepository;
import com.example.studyapp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(UserDto userDto) {
        User user = new User();
        Profile profile = new Profile();
        profile.setName(userDto.getName());
        profile.setLastname(userDto.getLastname());
        user.setProfile(profile);
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(Role.ROLE_USER);
        profile.setUser(user);
        return userRepository.save(user);
    }

}
