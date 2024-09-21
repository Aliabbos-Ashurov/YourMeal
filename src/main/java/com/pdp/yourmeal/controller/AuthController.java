package com.pdp.yourmeal.controller;

import com.pdp.yourmeal.dto.request.TokenRequestDTO;
import com.pdp.yourmeal.dto.request.UserRegisterDTO;
import com.pdp.yourmeal.dto.response.UserDTO;
import com.pdp.yourmeal.entity.User;
import com.pdp.yourmeal.service.AuthService;
import com.pdp.yourmeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  11:58
 **/
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO dto) {
        userService.save(dto);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody TokenRequestDTO dto) {
        Optional<User> user = authService.findByUsername(dto.username());
        if (user.isPresent()) {
            User user1 = user.get();
            if (user1.getPassword().equals(dto.password())) {
                return ResponseEntity.ok("SUCCESS");
            }
            return ResponseEntity.ok("FAIL");
        } else {
            return ResponseEntity.ok("FAIL");
        }
    }
}
