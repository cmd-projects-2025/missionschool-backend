package com.project.mission_school.bulletin.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BulletinBoardDto {
    private String writerId;
    private Long price;
    private String title;
    private String description;
    private Boolean bulletinState;
    private Integer viewCnt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
