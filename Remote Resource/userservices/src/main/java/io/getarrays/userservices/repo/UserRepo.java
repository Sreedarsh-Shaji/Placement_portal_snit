package io.getarrays.userservices.repo;

import io.getarrays.userservices.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByusername(String username);

}