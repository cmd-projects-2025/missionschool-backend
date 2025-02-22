package com.project.mission_school.bulletin.controller;

import com.project.mission_school.bulletin.dto.BulletinBoardDto;
import com.project.mission_school.bulletin.service.BulletinBoardService;
import com.project.mission_school.bulletin.entity.BulletinBoard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bulletin")
public class BulletinBoardController {

    private final BulletinBoardService bulletinBoardService;

    @GetMapping
    public ResponseEntity<List<BulletinBoardDto>> getAllBoard() {
        List<BulletinBoard> boardList = bulletinBoardService.getAllBoards();
        List<BulletinBoardDto> boardDtoList = boardList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(boardDtoList);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BulletinBoardDto> getBoardById(@PathVariable Long id) {
        BulletinBoard board = bulletinBoardService.getBoardById(id);
        bulletinBoardService.increaseViewCount(id);
        return ResponseEntity.ok(convertToDto(board));
    }

    @PostMapping("/write")
    public ResponseEntity<BulletinBoardDto> writeBoard(@RequestBody BulletinBoardDto postDto) {
        try {
            BulletinBoard savedBoard = bulletinBoardService.saveBoard(postDto);
            return ResponseEntity.ok(convertToDto(savedBoard));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BulletinBoard> updateBoard(@PathVariable Long id, @RequestBody BulletinBoardDto updateDto) {
        try {
            BulletinBoard updateBoard = bulletinBoardService.updateBoard(
                    id,
                    updateDto.getWriterId(),
                    updateDto.getPrice(),
                    updateDto.getTitle(),
                    updateDto.getDescription()
            );
            return ResponseEntity.ok(updateBoard);
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private BulletinBoardDto convertToDto(BulletinBoard board) {
        return BulletinBoardDto.builder()
                .writerId(board.getWriterId())
                .price(board.getPrice())
                .title(board.getTitle())
                .description(board.getDescription())
                .bulletinState(board.getBulletinState())
                .viewCnt(board.getViewCnt())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

}
