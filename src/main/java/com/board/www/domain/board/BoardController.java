package com.board.www.domain.board;

import com.board.www.comm.pageing.Criteria;
import com.board.www.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 리스트 조회
    @GetMapping("/board")
    public String getBoardList(Criteria cri, Model model) {
        System.out.println(cri.getKeyword());
        Map<String, Object> map = boardService.findByIdAll(cri);
        System.out.println(map.get("list"));
        System.out.println(map.get("pageMaker"));
        model.addAttribute("list", map.get("list"));
        model.addAttribute("pageMaker", map.get("pageMaker"));

        return "board";
    }

    // 게시글 조회
    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable("id") Long id) {
        boardService.findById(id);
        return "";
    }

    // 게시글 등록
    @PostMapping("/board")
    public String createBoard(@RequestBody Board board) {

        boardService.createBoard(board);
        return "";
    }

    // 게시글 수정
    @PatchMapping("/board")
    public String modifyBoard(@RequestBody Board board) {

        boardService.modifyBoard(board);
        return "";
    }

    // 게시글 삭제
    @DeleteMapping("/board")
    public String deleteBoard(@RequestBody Board board) {

        boardService.deleteBoard(board);
        return "";
    }

}