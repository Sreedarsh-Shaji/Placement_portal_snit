package com.jobseeker.candidate.jobseekercandidate.dao;

import com.jobseeker.candidate.jobseekercandidate.enums.ROLES;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin{
   private String uid;
   private String name;
   private String password;
   private String email;
   private String phone;
   private ROLES role = ROLES.ADMIN;
}