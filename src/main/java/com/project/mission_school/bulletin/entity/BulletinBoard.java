package com.project.mission_school.bulletin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BulletinBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writerId;

    private Long price;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean bulletinState;

    @Column(nullable = false)
    private Integer viewCnt = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void update(String writerId, Long price, String title, String description) {
        this.writerId = writerId;
        this.price = price;
        this.title = title;
        this.description = description;
    }

}
