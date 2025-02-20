package com.project.mission_school.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.mission_school.auth.dto.SignupRequest;
import com.project.mission_school.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignupController {

    private final UserService userService;
    
    @GetMapping("/signup")
    public ModelAndView showSignupForm() {
        return new ModelAndView("signup");
    }   
    

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("회원가입 성공: " + request.getUsername());
    }
    
}
