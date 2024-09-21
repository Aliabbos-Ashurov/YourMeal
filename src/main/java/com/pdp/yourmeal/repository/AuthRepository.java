package com.pdp.yourmeal.repository;

import com.pdp.yourmeal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:00
 **/
public interface AuthRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
