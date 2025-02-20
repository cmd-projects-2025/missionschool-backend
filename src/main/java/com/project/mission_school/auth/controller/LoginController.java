package com.project.mission_school.auth.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.mission_school.auth.dto.LoginRequest;
import com.project.mission_school.auth.util.JwtUtill;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtill jwtUtill;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtill.generateToken(request.getUsername());

            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true); // HTTPS 환경에서만 쿠키 전달
            jwtCookie.setPath("/");
            jwtCookie.setAttribute("SameSite", "Strict"); // CSRF 방지

            response.addCookie(jwtCookie);

            return "로그인 성공";
        } catch (BadCredentialsException e) {
            return "아이디 또는 비밀번호가 잘못되었습니다.";
        } catch (Exception e) {
            return "로그인 처리 중 오류가 발생했습니다.";
        }
    }
}
