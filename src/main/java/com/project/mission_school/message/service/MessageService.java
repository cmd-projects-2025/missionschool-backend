package com.project.mission_school.message.service;

import com.project.mission_school.message.entity.Message;
import com.project.mission_school.message.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public List<Message> getReceivedMessage(Long receiverId) {
        return messageRepository.findByReceiverIdOrderBySendTimeDesc(receiverId);
    }

    @Transactional
    public Message sendMessage(Message message) {
        message.setSendTime(LocalDateTime.now());
        message.setRead(false);

        return messageRepository.save(message);
    }

    @Transactional
    public void messageRead(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        message.setRead(true);
        messageRepository.save(message);
    }

}
