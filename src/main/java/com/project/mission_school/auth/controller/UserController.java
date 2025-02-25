package com.project.mission_school.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mission_school.auth.util.JwtUtill;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final JwtUtill jwtUtill;

    public UserController(JwtUtill jwtUtill) {
        this.jwtUtill = jwtUtill;
    }

    @GetMapping("/status")
    public ResponseEntity<?> checkLoginStatus(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("토큰이 없습니다.");
        }

        String token = authHeader.substring(7);

        if (!jwtUtill.validateToken(token)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String username = jwtUtill.getUsernameFromToken(token);
        return ResponseEntity.ok(username);
    }
}
