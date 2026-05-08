package com.api.user_api.repository;

import com.api.user_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email); // SELECT count(*) > 0 FROM table WHERE email = ?
}
