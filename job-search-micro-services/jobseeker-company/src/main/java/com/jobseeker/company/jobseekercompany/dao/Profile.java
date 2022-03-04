package com.jobseeker.company.jobseekercompany.dao;

import com.jobseeker.company.jobseekercompany.dto.Education;
import com.jobseeker.company.jobseekercompany.dto.Experience;
import com.jobseeker.company.jobseekercompany.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {

    List<Education> qualification;
    List<Experience> experience;
    List<Skill> skill;

}
