package com.jobseeker.candidate.jobseekercandidate.dao.profiles;

import com.jobseeker.candidate.jobseekercandidate.dto.Education;
import com.jobseeker.candidate.jobseekercandidate.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {

    List<Education> qualification;
    List<Skill> skill;
    Optional<Integer> minPay;
    Optional<Integer> maxPay;
    String locationOfEmployment;
    String modeOfEmployment;
    boolean shouldPossesPassport;
    boolean willingToRelocate;
    String linkedInProfile;

}
