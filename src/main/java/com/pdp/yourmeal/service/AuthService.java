package com.pdp.yourmeal.service;

import com.pdp.yourmeal.entity.User;
import com.pdp.yourmeal.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  12:00
 **/
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public Optional<User> findByUsername(String username) {
        return authRepository.findByUsername(username);
    }
}
