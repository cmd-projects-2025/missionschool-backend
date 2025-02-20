package com.project.mission_school.bulletin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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

    @GetMapping("/view/{id}")
    public ResponseEntity<BulletinBoard> getBoardById(@PathVariable Long id) {
        BulletinBoard board = bulletinBoardService.getBoardById(id);
        bulletinBoardService.increaseViewCount(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeBoard(@RequestBody BulletinBoard postboard) {
        try {
            postboard.setCreatedAt(LocalDateTime.now());
            postboard.setViewCnt(0);
            bulletinBoardService.saveBoard(postboard);
            return ResponseEntity.ok("게시글 작성 완료!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
