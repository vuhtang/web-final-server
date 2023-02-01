package com.vuhtang.lesha.repository;

import com.vuhtang.lesha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
