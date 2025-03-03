package com.project.mission_school.mypage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.mission_school.mypage.entity.MypageUser;

@Repository
public interface MypageUserRepository extends JpaRepository<MypageUser, Long> {
    Optional<MypageUser> findByUsername(String username);
}
