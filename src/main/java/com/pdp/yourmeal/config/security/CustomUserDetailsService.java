package com.pdp.yourmeal.config.security;

import com.pdp.yourmeal.entity.User;
import com.pdp.yourmeal.handler.exception.UserNotFoundException;
import com.pdp.yourmeal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Aliabbos Ashurov
 * @since 22/September/2024  10:01
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with name: {0}", username));
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword());
    }
}
