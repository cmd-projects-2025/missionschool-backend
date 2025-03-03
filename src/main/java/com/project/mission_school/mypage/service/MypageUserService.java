package com.project.mission_school.mypage.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mission_school.mypage.entity.MypageUser;
import com.project.mission_school.mypage.repository.MypageUserRepository;

@Service
public class MypageUserService {
    private final MypageUserRepository mypageUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MypageUserService(MypageUserRepository mypageUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.mypageUserRepository = mypageUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MypageUser findByUsername(String username) {
        return mypageUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void updateUserInfo(Long id, String username, String password, String phonenumber, String nickname, String village) {
        MypageUser user = mypageUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (phonenumber != null && !phonenumber.isEmpty()) {
            user.setPhonenumber(phonenumber);
        }
        if (nickname != null && !nickname.isEmpty()) {
            user.setNickname(nickname);
        }
        if (village != null && !village.isEmpty()) {
            user.setVillage(village);
        }
    }

    @Transactional
    public void deleteUser(Long userId) {
        MypageUser user = mypageUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        mypageUserRepository.delete(user);
    }
}
