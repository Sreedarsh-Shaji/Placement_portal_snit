package com.jobseeker.candidate.jobseekercandidate.controller;

import com.jobseeker.candidate.jobseekercandidate.dao.User;
import com.jobseeker.candidate.jobseekercandidate.dto.LoginRequest;
import com.jobseeker.candidate.jobseekercandidate.services.UserAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/api/v1/userAuth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService service;

    @PostMapping("/signup-user")
    public ResponseEntity<User> signupUser(@RequestBody User newUser) throws ExecutionException, InterruptedException {
        newUser.setUid(UUID.randomUUID().toString());
        service.saveUserDetails(newUser);
        return new ResponseEntity<User>( newUser , HttpStatus.OK );
    }

    @PostMapping("/login-user")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest newUser) throws ExecutionException, InterruptedException {
        return new ResponseEntity<User>( service.loginUser(newUser) , HttpStatus.OK );
    }

    /*
    * This is a test program
    * */

    @GetMapping("/{id}/all")
    public List<User> getAll(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        if( id.equals("pass") )
        {
            return service.getAllStudents();
        }
        return null;
    }

}
