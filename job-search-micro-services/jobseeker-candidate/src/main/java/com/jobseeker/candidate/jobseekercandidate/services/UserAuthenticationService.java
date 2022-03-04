package com.jobseeker.candidate.jobseekercandidate.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.candidate.jobseekercandidate.dao.User;
import com.jobseeker.candidate.jobseekercandidate.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserAuthenticationService {

    public static final String COL_NAME="js-student";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    /* Saves all student details */
    public String saveUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(user.getUid()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    /* saves user details */
    public User loginUser(LoginRequest request) throws ExecutionException, InterruptedException {
        boolean isValid = false;

        List<User> users = this.getAllStudents();
        users.forEach( user -> log.info(user.toString()) );
        List<User> validUser = users.stream().
                filter( user -> user.getEmail().equals(request.getUsername())&& user.getPassword().equals(request.getUsername())
                 ).collect(Collectors.toList());
        return ( validUser.size() > 0) ? validUser.get(0) : null;

    }

    public List<User> getAllStudents() throws ExecutionException, InterruptedException {
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(User.class));
        }
        return documents.stream().map( a -> a.toObject(User.class)).collect(Collectors.toList());
    }

}
