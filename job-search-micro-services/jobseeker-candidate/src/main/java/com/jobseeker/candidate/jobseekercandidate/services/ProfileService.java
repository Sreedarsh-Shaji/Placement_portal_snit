package com.jobseeker.candidate.jobseekercandidate.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.candidate.jobseekercandidate.dao.profiles.Profile;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProfileService {

    public static final String COL_NAME="js-profile";

    /** This will save the profile details into firebase */
    public String saveProfileDetails(Profile profile) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(profile.getUser().getName()).set(profile);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}
