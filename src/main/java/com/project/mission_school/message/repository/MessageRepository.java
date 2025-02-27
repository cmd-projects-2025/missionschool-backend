package com.project.mission_school.message.repository;

import com.project.mission_school.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverIdOrderBySendTimeDesc(Long receiverId);
}
