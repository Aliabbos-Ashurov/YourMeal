package com.pdp.yourmeal.config;

import org.springframework.stereotype.Component;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  13:36
 **/
@Component
@SuppressWarnings("unused")
public class SessionUser {

//    public CustomUserDetails user() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails ud)
//            return (CustomUserDetails) ud;
//        return null;
//    }

    public Long id() {
//        CustomUserDetails user = user();
//        if (Objects.isNull(user))
//            return -1L;
//        return user.getId();
        return 0L;
    }
}
