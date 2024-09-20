package com.pdp.yourmeal.repository;

import com.pdp.yourmeal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByPhone(String phone);

    @Query("FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
    Optional<User> findByFirstAndLastName(String first, String lastName);
}