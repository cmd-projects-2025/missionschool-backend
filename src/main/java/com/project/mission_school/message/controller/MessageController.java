package com.project.mission_school.message.controller;


import com.project.mission_school.message.entity.Message;
import com.project.mission_school.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bulletin/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getReceivedMessage(@RequestParam Long receiverId) {
        List<Message> messages = messageService.getReceivedMessage(receiverId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @PatchMapping("/read/{messageId}")
    public ResponseEntity<Void> readMessage(@PathVariable Long messageId) {
        messageService.messageRead(messageId);
        return ResponseEntity.noContent().build();
    }

}
