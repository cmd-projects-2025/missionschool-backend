package com.project.mission_school.message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @NotNull(message = "Sender ID는 생략될 수 없습니다.")
    private Long senderId;
    @NotNull(message = "Receiver ID는 생략될 수 없습니다.")
    private Long receiverId;

    @Column(length = 500)
    @NotBlank(message = "빈 content는 허용되지 않습니다.")
    private String content;

    private LocalDateTime sendTime;

    private boolean isRead = false;

}
