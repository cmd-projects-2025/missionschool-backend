package com.project.mission_school.bulletin.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mission_school.bulletin.entity.BulletinBoard;
import com.project.mission_school.bulletin.service.BulletinBoardService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bulletin")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @GetMapping
    public ResponseEntity<Page<BulletinBoard>> getAllBoard(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BulletinBoard> boardPage = bulletinBoardService.getAllBoards(pageable);
        return ResponseEntity.ok(boardPage);
    }

    @GetMapping("/urgent")
    public ResponseEntity<List<BulletinBoard>> UrgentBoards() {
        List<BulletinBoard> urgentBoardList = bulletinBoardService.UrgentBoards();
        return ResponseEntity.ok(urgentBoardList);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> getBoardById(@PathVariable("id") Long id) {
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
