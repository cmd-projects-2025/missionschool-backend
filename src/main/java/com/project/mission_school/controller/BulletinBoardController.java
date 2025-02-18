package com.project.mission_school.controller;

import com.project.mission_school.service.BulletinBoardService;
import com.project.mission_school.entity.BulletinBoard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @GetMapping
    public ResponseEntity<List<BulletinBoard>> getAllBoard() {
        List<BulletinBoard> boards = bulletinBoardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }
}
