package io.getarrays.userservices.repo;

import io.getarrays.userservices.domains.Role;
import io.getarrays.userservices.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByname(String name);

}