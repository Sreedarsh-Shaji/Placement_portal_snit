package com.jobseeker.candidate.jobseekercandidate.dao.profiles;

import com.sun.tools.javac.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedProfile extends ShortListedProfile{

    List<PersonalDocument> personalDocuments;

}


class PersonalDocument{
    String idProof;
    String location;
}
