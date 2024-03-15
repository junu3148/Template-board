package com.board.www.domain.board;

import com.board.www.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 리스트 조회
    @GetMapping("/board")
    public ResponseEntity<List<Board>> getBoardList() {
        return boardService.findByIdAll();
    }

    // 게시글 조회
    @GetMapping("/board/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable("id") Long id) {
        return boardService.findById(id);
    }

    // 게시글 등록
    @PostMapping("/board")
    public ResponseEntity<Boolean> createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // 게시글 수정
    @PatchMapping("/board")
    public ResponseEntity<Boolean> modifyBoard(@RequestBody Board board) {
        return boardService.modifyBoard( board);
    }

    // 게시글 삭제
    @DeleteMapping("/board")
    public ResponseEntity<Boolean> deleteBoard(@RequestBody Board board) {
        return boardService.deleteBoard(board);
    }

}