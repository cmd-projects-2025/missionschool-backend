package com.project.mission_school.service;

import com.project.mission_school.entity.BulletinBoard;
import com.project.mission_school.repository.BulletinBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BulletinBoardService {

    private final BulletinBoardRepository bulletinBoardRepository;

    @Transactional
    public BulletinBoard saveBoard(BulletinBoard board) {
        board.setCreatedAt(LocalDateTime.now());
        board.setViewCnt(0);
        board.setBulletinState(true);
        return bulletinBoardRepository.save(board);
    }

    public List<BulletinBoard> getAllBoards() {
        return bulletinBoardRepository.findAll();
    }

    public BulletinBoard getBoardById(Long id) {
        return bulletinBoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + id));
    }

    @Transactional
    public void increaseViewCount(Long boardId) {
        BulletinBoard board = getBoardById(boardId);
        board.setViewCnt(board.getViewCnt() + 1);
    }

    @Transactional
    public BulletinBoard updateBoard(Long boardId, String title, String description) {
        BulletinBoard board = getBoardById(boardId);
        board.update(title, description);
        board.setUpdatedAt(LocalDateTime.now());
        return board;
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        if (!bulletinBoardRepository.existsById(boardId)) {
            throw new EntityNotFoundException("게시글이 존재하지 않습니다. ID: " + boardId);
        }
        bulletinBoardRepository.deleteById(boardId);
    }


}
