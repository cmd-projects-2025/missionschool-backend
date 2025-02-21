package com.project.mission_school.mypage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mission_school.mypage.entity.User;

@Repository
public interface MypageUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}