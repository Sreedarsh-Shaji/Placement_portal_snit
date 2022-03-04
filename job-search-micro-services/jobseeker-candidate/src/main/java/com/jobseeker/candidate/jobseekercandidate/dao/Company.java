package com.jobseeker.candidate.jobseekercandidate.dao;

import com.jobseeker.candidate.jobseekercandidate.enums.ROLES;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company extends User {
   private ROLES role = ROLES.COMPANY;
}