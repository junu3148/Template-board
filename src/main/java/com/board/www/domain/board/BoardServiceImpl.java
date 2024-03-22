package com.board.www.domain.board;

import com.board.www.comm.pageing.Criteria;
import com.board.www.comm.pageing.PageMakerDTO;
import com.board.www.domain.board.entity.Board;
import com.board.www.eu.BoardStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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

        Pageable pageable = cri.toPageable(); // Criteria 객체에서 Pageable 객체 생성
        Page<Board> page=null; // 조회 결과를 담을 Page 객체 초기화

        // 페이징 처리를 위해 조건에 맞는 게시글 목록 조회
        if (cri.getKeyword() == null || cri.getKeyword().isEmpty()) {
            page = boardRepository.findByBoardStatus(BoardStatus.ACTIVE, pageable);
        } else {
            page = boardRepository.findByKeywordAndBoardStatus(cri.getKeyword(), BoardStatus.ACTIVE, pageable);
        }

        // 조회된 결과에서 총 게시물 수 및 게시물 목록 추출
        int total = (int) page.getTotalElements(); // 총 게시물 수
        List<Board> boardList = page.getContent(); // 현재 페이지에 해당하는 게시물 목록

        // 페이징 정보 생성
        PageMakerDTO pageMaker = new PageMakerDTO(total, cri);

        // 결과 Map에 페이징 정보와 게시물 목록 추가
        map.put("pageMaker", pageMaker);
        map.put("list", boardList);

        return map;
    }

    // 게시글 조회
    @Override
    @Transactional
    public Board findById(Long id) {
        // ID에 해당하는 게시글을 조회 전에 먼저 조회수를 1 증가
        boardRepository.incrementViewCount(id);

        // ID에 해당하는 게시글을 조회
        Optional<Board> board = boardRepository.findById(id);

        // Optional<Board>를 Board로 변환하여 반환
        // 찾는 게시글이 없으면 예외를 발생시킵니다.
        return board.orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
    }

    // 게시글 등록
    @Override
    @Transactional
    public boolean createBoard(Board board) {
        // 게시글 생성
        boardRepository.save(board);
        return true; // 저장이 성공적으로 완료되면 true 반환
    }

    // 게시글 수정
    @Override
    @Transactional
    public boolean modifyBoard(Board board) {
        // 게시글이 존재하는지 확인 후 수정
        if (boardRepository.existsById(board.getBoardKey())) {
            boardRepository.save(board); // save 메소드는 엔티티가 존재하면 업데이트, 없으면 새로 생성합니다.
            return true; // 수정이 성공적으로 완료되면 true 반환
        } else {
            return false; // 해당 게시글이 존재하지 않으면 false 반환
        }
    }

    // 게시글 삭제
    @Override
    @Transactional
    public boolean deleteBoard(Board board) {
        // 게시글이 존재하는지 확인 후 삭제
        if (boardRepository.existsById(board.getBoardKey())) {
            boardRepository.deleteById(board.getBoardKey());
            return true; // 삭제가 성공적으로 완료되면 true 반환
        } else {
            return false; // 해당 게시글이 존재하지 않으면 false 반환
        }
    }

}

