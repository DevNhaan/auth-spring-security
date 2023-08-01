package com.dev.authspringsecurity.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SUser, String> {
    Optional<SUser> findByUsername(String username);
}
