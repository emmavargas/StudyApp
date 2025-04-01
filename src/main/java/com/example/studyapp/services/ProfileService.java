package com.example.studyapp.services;

import com.example.studyapp.entities.Profile;
import com.example.studyapp.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Profile> getProfileId(Long idProfile){
        return profileRepository.findById(idProfile);
    }



}
