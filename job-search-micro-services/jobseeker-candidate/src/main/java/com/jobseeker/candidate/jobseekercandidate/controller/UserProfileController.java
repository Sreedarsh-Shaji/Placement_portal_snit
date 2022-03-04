package com.jobseeker.candidate.jobseekercandidate.controller;

import com.jobseeker.candidate.jobseekercandidate.dao.profiles.Profile;
import com.jobseeker.candidate.jobseekercandidate.services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/api/v1/userprofile")
@RequiredArgsConstructor
public class UserProfileController {

    private final ProfileService service;

    @PostMapping("/save")
    ResponseEntity<?> setProfile(@RequestBody Profile profile) throws ExecutionException, InterruptedException {
        log.info(profile.getUser().toString());
        service.saveProfileDetails(profile);
        return new ResponseEntity<>("Profile set", HttpStatus.OK);
    }

}
