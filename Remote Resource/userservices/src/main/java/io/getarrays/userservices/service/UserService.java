package io.getarrays.userservices.service;

import io.getarrays.userservices.domains.Role;
import io.getarrays.userservices.domains.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
