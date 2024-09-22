package com.pdp.yourmeal.controller;

import com.pdp.yourmeal.dto.request.TokenRequestDTO;
import com.pdp.yourmeal.dto.request.UserRegisterDTO;
import com.pdp.yourmeal.dto.response.TokensDTO;
import com.pdp.yourmeal.service.UserService;
import com.pdp.yourmeal.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aliabbos Ashurov
 * @since 21/September/2024  11:58
 **/
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;


    @Operation(summary = "Register a new user", description = "Creates a new user with the provided details.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO dto) {
        userService.save(dto);
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "Generate access and refresh tokens", description = "Authenticates the user and generates a pair of JWT tokens (access and refresh).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tokens generated successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password"),
    })
    @PostMapping("/token")
    public ResponseEntity<TokensDTO> token(@RequestBody TokenRequestDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authenticationManager.authenticate(authenticationToken);
        String accessToken = jwtTokenUtil.generateAccessToken(dto.username());
        return ResponseEntity.ok(new TokensDTO(accessToken));
    }
}
