package com.pdp.yourmeal.config;

import com.pdp.yourmeal.config.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  13:36
 **/
@Component
public class SessionUser {


    public CustomUserDetails user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails ud)
            return (CustomUserDetails) ud;
        return null;
    }

    public Long id() {
        CustomUserDetails user = user();
        if (Objects.isNull(user))
            return -1L;
        return user.getId();
    }
}