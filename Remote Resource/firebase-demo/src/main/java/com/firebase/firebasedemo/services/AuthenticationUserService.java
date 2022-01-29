package com.firebase.firebasedemo.services;

import com.google.firebase.auth.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class AuthenticationUserService {

    public void getUserById(String uid) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
        log.info("Successfully fetched user data: " + userRecord.toString());
    }

    public void getUserByEmail(String email) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
        log.info("Successfully fetched user data: " + userRecord.toString());
    }

/*
    You can also retrieve a list of users based on identifiers that you provide. You may identify users by their uid, email, or phone number. A maximum of 100 identifiers may be supplied in a single call. Identifiers can contain a mix of types:
*/
    public void getBulkData() throws FirebaseAuthException, ExecutionException, InterruptedException {
        GetUsersResult result = FirebaseAuth.getInstance().getUsersAsync(Arrays.asList(
                new UidIdentifier("uid1"),
                new EmailIdentifier("user2@example.com"),
                new PhoneIdentifier("+15555550003"),
                new ProviderIdentifier("google.com", "google_uid4"))).get();

        System.out.println("Successfully fetched user data:");
        for (UserRecord user : result.getUsers()) {
            System.out.println(user.getUid());
        }

        System.out.println("Unable to find users corresponding to these identifiers:");
        for (UserIdentifier uid : result.getNotFound()) {
            System.out.println(uid);
        }
    }

    public void listAllUsers() throws FirebaseAuthException {
        // Start listing users from the beginning, 1000 at a time.
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                System.out.println("User 1: " + user.getUid());
            }
            page = page.getNextPage();
        }

// Iterate through all users. This will still retrieve users in batches,
// buffering no more than 1000 users in memory at a time.
        page = FirebaseAuth.getInstance().listUsers(null);
        for (ExportedUserRecord user : page.iterateAll()) {
            System.out.println("User 2: " + user.getUid());
        }
    }

    public void saveAUser() throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail("user@example.com")
                .setEmailVerified(false)
                .setPassword("secretPassword")
                .setPhoneNumber("+11234567890")
                .setDisplayName("John Doe")
                .setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Successfully created new user: " + userRecord.getUid());
    }

    /*
    *
    * You can update any of the user properties below. All properties are optional. If a property is not specified, its value will remain unchanged.

Property	Type	Description
email	string	The user's new primary email. Must be a valid email address.
emailVerified	boolean	Whether or not the user's primary email is verified. If not provided, the default is false.
phoneNumber	string	The user's new primary phone number. Must be a valid E.164 spec compliant phone number. Set to null to clear the user's existing phone number.
password	string	The user's new raw, unhashed password. Must be at least six characters long.
displayName	string | null	The users' new display name. Set to null to clear the user's existing display name.
photoURL	string | null	The users' new photo URL. Set to null to clear the user's existing photo URL. If non-null, must be a valid URL.
disabled	boolean	Whether or not the user is disabled. true for disabled; false for enabled.
    *
    * */

    public void UpdateUser(String uid) throws FirebaseAuthException {
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                .setEmail("user@example.com")
                .setPhoneNumber("+11234567890")
                .setEmailVerified(true)
                .setPassword("newPassword")
                .setDisplayName("Jane Doe")
                .setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(true);

        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
        System.out.println("Successfully updated user: " + userRecord.getUid());
    }

    public void deleteAUser(String uid) throws FirebaseAuthException {
        FirebaseAuth.getInstance().deleteUser(uid);
        System.out.println("Successfully deleted user.");
    }

    public void deleteMultipleUsers() throws ExecutionException, InterruptedException {
        DeleteUsersResult result = FirebaseAuth.getInstance().deleteUsersAsync(
                Arrays.asList("uid1", "uid2", "uid3")).get();

        System.out.println("Successfully deleted " + result.getSuccessCount() + " users");
        System.out.println("Failed to delete " + result.getFailureCount() + " users");
        for (ErrorInfo error : result.getErrors()) {
            System.out.println("error #" + error.getIndex() + ", reason: " + error.getReason());
        }
    }

}
