package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.Admin;
import com.jobseeker.company.jobseekercompany.dto.Credentials;
import com.jobseeker.company.jobseekercompany.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @PostMapping("/save-admin")
    private ResponseEntity<String> setAdmin(@RequestBody Admin admin) throws ExecutionException, InterruptedException {
        admin.setUid(UUID.randomUUID().toString());
        service.saveAdminDetails(admin);
        return  new ResponseEntity<String>("Saved admin", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-admins")
    private ResponseEntity<List<Admin>> seeAllAdmin() throws ExecutionException, InterruptedException {
        return new ResponseEntity<List<Admin>>( service.getAllAdmins() , HttpStatus.OK );
    }

    @PostMapping("/admin-login")
    private ResponseEntity<Boolean> adminLogin(@RequestBody Credentials credentials) throws ExecutionException, InterruptedException {
        return new ResponseEntity<Boolean>( service.adminLogin(credentials.getUsername(),credentials.getPassword()) , HttpStatus.OK);
    }



}