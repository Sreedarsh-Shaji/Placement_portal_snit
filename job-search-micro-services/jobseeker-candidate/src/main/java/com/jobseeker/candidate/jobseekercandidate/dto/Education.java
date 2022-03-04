package com.jobseeker.candidate.jobseekercandidate.dto;

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
public class Education {

    String course;
    String specialisation;
    Optional<Integer> endingYear;

}
