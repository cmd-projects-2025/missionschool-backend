package com.project.mission_school.bulletin.service;

import com.project.mission_school.bulletin.entity.BulletinBoard;
import com.project.mission_school.bulletin.repository.BulletinBoardRepository;
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
    public BulletinBoard saveBoard(BulletinBoard postboard) {

        postboard.setWriterId(postboard.getWriterId());
        postboard.setPrice(postboard.getPrice());
        postboard.setTitle(postboard.getTitle());
        postboard.setDescription(postboard.getDescription());
        postboard.setCreatedAt(LocalDateTime.now());
        postboard.setViewCnt(0);
        postboard.setBulletinState(true);

        if (postboard.getUrgent()) {
            postboard.setUrgent(true);
        }

        return bulletinBoardRepository.save(postboard);
    }

    public List<BulletinBoard> getAllBoards() {
        return bulletinBoardRepository.findAll();
    }

    public BulletinBoard getBoardById(Long id) {
        return bulletinBoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + id));
    }

    @Transactional
    public void increaseViewCount(Long id) {
        BulletinBoard board = getBoardById(id);
        board.setViewCnt(board.getViewCnt() + 1);
    }

    @Transactional
    public BulletinBoard updateBoard(Long id, BulletinBoard updateBoard) {

        BulletinBoard updateboard = getBoardById(id);

        updateboard.setWriterId(updateBoard.getWriterId());
        updateboard.setPrice(updateBoard.getPrice());
        updateboard.setTitle(updateBoard.getTitle());
        updateboard.setDescription(updateBoard.getDescription());
        updateboard.setUpdatedAt(LocalDateTime.now());
        return bulletinBoardRepository.save(updateboard);
    }

    @Transactional
    public void deleteBoard(Long id) {
        bulletinBoardRepository.deleteById(id);
    }

    @Transactional
    public List<BulletinBoard> UrgentBoards() {
        return bulletinBoardRepository.findByUrgentTrueOrderByCreatedAt();
    }

}
