package com.project.mission_school.mypage.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mission_school.mypage.repository.MypageUserRepository;
import com.project.mission_school.mypage.entity.MypageUser;

@Service
public class MypageUserService {
    private final MypageUserRepository mypageUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MypageUserService(MypageUserRepository mypageUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.mypageUserRepository = mypageUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void updateUserInfo(Long id, String username, String password, String phonenumber) {
        MypageUser mypageUser = mypageUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (username != null && !username.isEmpty()) {
            mypageUser.setUsername(username);
        }

        if (password != null && !password.isEmpty()) {
            mypageUser.setPassword(passwordEncoder.encode(password));
        }

        if (phonenumber != null && !phonenumber.isEmpty()) {
            mypageUser.setPhonenumber(phonenumber);
        }
        
    }

    @Transactional
    public void deleteUser(Long userId) {
        MypageUser user = mypageUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        mypageUserRepository.delete(user);
    }
}
