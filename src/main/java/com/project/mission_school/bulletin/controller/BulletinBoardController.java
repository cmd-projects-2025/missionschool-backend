package com.project.mission_school.bulletin.controller;

import com.project.mission_school.bulletin.service.BulletinBoardService;
import com.project.mission_school.bulletin.entity.BulletinBoard;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bulletin")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @GetMapping
    public ResponseEntity<List<BulletinBoard>> getAllBoard() {
        List<BulletinBoard> boardList = bulletinBoardService.getAllBoards();
        return ResponseEntity.ok(boardList);
    }

    @GetMapping("/urgent")
    public ResponseEntity<List<BulletinBoard>> UrgentBoards() {
        List<BulletinBoard> urgentBoardList = bulletinBoardService.UrgentBoards();
        return ResponseEntity.ok(urgentBoardList);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> getBoardById(@PathVariable Long id) {
        try {
            BulletinBoard board = bulletinBoardService.getBoardById(id);
            bulletinBoardService.increaseViewCount(id);
            return ResponseEntity.ok(board);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/write")
    public ResponseEntity<BulletinBoard> writeBoard(@RequestBody BulletinBoard postboard) {
        try {
            BulletinBoard savedBoard = bulletinBoardService.saveBoard(postboard);
            return ResponseEntity.ok(savedBoard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BulletinBoard> updateBoard(@PathVariable Long id, @RequestBody BulletinBoard updateBoard) {
        try {
            BulletinBoard updatedBoard = bulletinBoardService.updateBoard(id, updateBoard);
            return ResponseEntity.ok(updatedBoard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        try {
            bulletinBoardService.deleteBoard(id);
            return ResponseEntity.ok("게시글 삭제 완료!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
