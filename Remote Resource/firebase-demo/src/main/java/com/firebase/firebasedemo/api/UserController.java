package com.firebase.firebasedemo.api;

import com.firebase.firebasedemo.services.AuthenticationUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    AuthenticationUserService userService;

    @GetMapping("/user-by-id/{uid}")
    public void getUserById(@PathVariable String uid)
    {
        try{
            userService.getUserById(uid);
        }
        catch(Exception exception)
        {
            log.info(exception.getMessage());
        }

    }

    @GetMapping("/list-all-users")
    public void listAllUsers()
    {
        try{
            userService.listAllUsers();
        }
        catch(Exception exception)
        {
            log.info(exception.getMessage());
        }

    }

}
