package com.project.mission_school.bulletin.service;

import com.project.mission_school.bulletin.dto.BulletinBoardDto;
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
    public BulletinBoard saveBoard(BulletinBoardDto postDto) {
        BulletinBoard postboard = new BulletinBoard();

        postboard.setWriterId(postDto.getWriterId());
        postboard.setPrice(postDto.getPrice());
        postboard.setTitle(postDto.getTitle());
        postboard.setDescription(postDto.getDescription());

        postboard.setCreatedAt(LocalDateTime.now());
        postboard.setViewCnt(0);
        postboard.setBulletinState(true);

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
    public void increaseViewCount(Long boardId) {
        BulletinBoard board = getBoardById(boardId);
        board.setViewCnt(board.getViewCnt() + 1);
    }

    @Transactional
    public BulletinBoard updateBoard(Long boardId, String writerId, Long price, String title, String description) {
        BulletinBoard board = getBoardById(boardId);
        board.update(writerId, price, title, description);
        board.setUpdatedAt(LocalDateTime.now());
        return board;
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        bulletinBoardRepository.deleteById(boardId);
    }


}
