package com.project.mission_school.bulletin.repository;

import com.project.mission_school.bulletin.entity.BulletinBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long> {
}
