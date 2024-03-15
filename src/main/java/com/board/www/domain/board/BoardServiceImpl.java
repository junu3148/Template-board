package com.board.www.domain.board;

import com.board.www.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    // 게시글 리스트 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Board>> findByIdAll() {
        // 모든 게시글을 조회
        List<Board> boards = boardRepository.findAll();
        return ResponseEntity.ok().body(boards);
    }

    // 게시글 조회
    @Override
    @Transactional
    public ResponseEntity<Board> findById(Long id) {

        // ID에 해당하는 게시글을 조회 전에 먼저 조회수를 1 증가
        boardRepository.incrementViewCount(id);

        // ID에 해당하는 게시글을 조회
        Optional<Board> board = boardRepository.findById(id);
        return board.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 등록
    @Override
    @Transactional
    public ResponseEntity<Boolean> createBoard(Board board) {
        // 게시글 생성
        boardRepository.save(board);
        return ResponseEntity.ok().body(true);
    }

    // 게시글 수정
    @Override
    @Transactional
    public ResponseEntity<Boolean> modifyBoard(Board board) {
        // 게시글이 존재하는지 확인 후 수정
        Optional<Board> existingBoard = boardRepository.findById(board.getBoardKey());
        if (existingBoard.isPresent()) {
            boardRepository.save(board); // save 메소드는 엔티티가 존재하면 업데이트, 없으면 새로 생성합니다.
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제
    @Override
    @Transactional
    public ResponseEntity<Boolean> deleteBoard(Board board) {
        // 게시글이 존재하는지 확인 후 삭제
        Optional<Board> existingBoard = boardRepository.findById(board.getBoardKey());
        if (existingBoard.isPresent()) {
            boardRepository.delete(board);
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

