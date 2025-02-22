package com.project.mission_school.mypage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mission_school.mypage.entity.MypageUser;
import com.project.mission_school.mypage.service.MypageUserService;

@RestController
@RequestMapping("/api/user")
public class MypageMainController {
    private final MypageUserService mypageUserService;

    public MypageMainController(MypageUserService mypageUserService) {
        this.mypageUserService = mypageUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<MypageUser> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        MypageUser user = mypageUserService.findByUsername(username);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
    if (authentication == null || !authentication.isAuthenticated()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    String username = authentication.getName();

    MypageUser user = mypageUserService.findByUsername(username);

    mypageUserService.deleteUser(user.getId());
    
    return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}
