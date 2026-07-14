package com.employee.controller;

import com.employee.config.JwtUtil;
import com.employee.dto.LoginRequest;
import com.employee.dto.LoginResponse;
import com.employee.entity.User;
import com.employee.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        User user = service.getUser(request.getUserName())
                .orElseThrow(
                        () -> new RuntimeException("User not found"));

        boolean valid = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!valid) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        String token = jwtUtil.generateToken(
                user.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
