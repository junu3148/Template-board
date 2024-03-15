package com.board.www.domain.board;

import com.board.www.comm.pageing.Criteria;
import com.board.www.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    // 게시글 리스트 조회
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findByIdAll(Criteria cri) {
        Map<String, Object> map = new HashMap<>();

    /*    // 페이징 처리를 위해 전체 게시글 수 조회
        int total = boardRepository.countByCriteria(cri); // 가정: boardRepository에 해당 메서드 구현이 필요

        // 조건에 맞는 게시글 목록 조회
        List<Board> boards = boardRepository.findAllByCriteria(cri); // 가정: boardRepository에 해당 메서드 구현이 필요

        // 페이징 정보 생성
        PageMakerDTO pageMake = new PageMakerDTO(total, cri);
        map.put("pageMake", pageMake);
        map.put("list", boards);
*/
        return map;
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

