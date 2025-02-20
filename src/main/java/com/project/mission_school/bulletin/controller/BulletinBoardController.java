package com.project.mission_school.bulletin.controller;

import com.project.mission_school.bulletin.dto.BulletinBoardDto;
import com.project.mission_school.bulletin.service.BulletinBoardService;
import com.project.mission_school.bulletin.entity.BulletinBoard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bulletin")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @GetMapping
    public ResponseEntity<List<BulletinBoardDto>> getAllBoard() {
        List<BulletinBoard> boardList = bulletinBoardService.getAllBoards();

        List<BulletinBoardDto> boardDtoList = boardList.stream().map(board -> {
            BulletinBoardDto boarddto = new BulletinBoardDto();
            boarddto.setWriterId(board.getWriterId());
            boarddto.setPrice(board.getPrice());
            boarddto.setTitle(board.getTitle());
            boarddto.setDescription(board.getDescription());
            boarddto.setBulletinState(board.getBulletinState());
            boarddto.setViewCnt(board.getViewCnt());
            boarddto.setCreatedAt(board.getCreatedAt());
            boarddto.setUpdatedAt(board.getUpdatedAt());
            return boarddto;
        }).toList();

        return ResponseEntity.ok(boardDtoList);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BulletinBoard> getBoardById(@PathVariable Long id) {
        BulletinBoard board = bulletinBoardService.getBoardById(id);
        bulletinBoardService.increaseViewCount(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeBoard(@RequestBody BulletinBoardDto postDto) {
        try {
            BulletinBoard postboard = new BulletinBoard();

            postboard.setWriterId(postDto.getWriterId());
            postboard.setPrice(postDto.getPrice());
            postboard.setTitle(postDto.getTitle());
            postboard.setDescription(postDto.getDescription());

            bulletinBoardService.saveBoard(postboard);

            return ResponseEntity.ok("게시글 작성 완료!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BulletinBoard updateBoard) {
        try {
            bulletinBoardService.updateBoard(id, updateBoard.getTitle(), updateBoard.getDescription());
            return ResponseEntity.ok("게시글 수정 완료!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        try {
            bulletinBoardService.deleteBoard(id);
            return ResponseEntity.ok("게시글 삭제 완료!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
