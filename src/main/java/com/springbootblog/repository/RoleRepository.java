package com.springbootblog.repository;

import com.springbootblog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
//    Optional<Role> findByName(String name);
}
