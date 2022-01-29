package io.getarrays.userservices.service;

import io.getarrays.userservices.domains.Role;
import io.getarrays.userservices.domains.User;
import io.getarrays.userservices.repo.RoleRepo;
import io.getarrays.userservices.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor//It will create a constructor and pass everything as autowired
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user into the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role into the database");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByusername(username);
        Role role = roleRepo.findByname(roleName);
        log.info("Adding role {} to  user {}" , roleName , username );
        user.getRoles().add(role);
        userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}" , username);
        return userRepo.findByusername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    //Implements un implemented methods of the UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepo.findByusername(username);
        if(user == null){
            log.error("User not found exception");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.error("User found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername() ,  user.getPassword() , authorities);
    }
}
