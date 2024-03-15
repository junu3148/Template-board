package com.board.www.domain.board;

import com.board.www.domain.board.entity.Board;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {

    /**
     * 모든 게시판 항목의 목록을 찾아 클라이언트에게 반환합니다.
     * <p>
     * 이 메서드는 데이터베이스에서 모든 게시판 항목을 조회하여 그 목록을
     * ResponseEntity 형태로 클라이언트에게 제공합니다. 이 과정에서
     * 데이터베이스 접근 오류 또는 기타 예외가 발생할 수 있으며, 이 경우
     * 적절한 예외 처리와 함께 에러 메시지를 담은 ResponseEntity가 반환될 수 있습니다.
     */
    ResponseEntity<List<Board>> findByIdAll();

    /**
     * 특정 ID를 가진 게시판 항목을 찾아 클라이언트에게 반환합니다.
     * <p>
     * 클라이언트로부터 게시판 항목의 고유 식별자인 ID를 전달받아,
     * 해당 ID와 일치하는 게시판 항목을 데이터베이스에서 조회합니다. 조회된
     * 게시판 항목은 ResponseEntity 형태로 클라이언트에게 제공됩니다.
     * 만약 해당 ID를 가진 게시판 항목이 없을 경우, 적절한 상태 코드와 함께
     * 에러 메시지를 담은 ResponseEntity가 반환될 수 있습니다.
     *
     * @param id 찾고자 하는 게시판 항목의 ID
     * @return 특정 ID를 가진 게시판 항목, 또는 에러 메시지를 포함한 ResponseEntity 객체
     */
    ResponseEntity<Board> findById(Long id);

    /**
     * 새로운 게시판 항목을 생성합니다.
     * <p>
     * 클라이언트로부터 게시판 항목에 해당하는 정보를 담은 Board 객체를 전달받아,
     * 이를 데이터베이스에 저장합니다. 성공적으로 저장되었을 경우, 성공 메시지와 함께
     * true 값을 담은 ResponseEntity를 반환합니다. 저장 과정에서 오류가 발생할 경우,
     * false 값을 담은 ResponseEntity와 함께 적절한 에러 메시지를 반환할 수 있습니다.
     *
     * @param board 클라이언트로부터 받은 게시판 항목 정보
     * @return 생성 성공 여부를 나타내는 Boolean 값을 포함한 ResponseEntity 객체
     */
    ResponseEntity<Boolean> createBoard(Board board);

    /**
     * 기존 게시판 항목을 수정합니다.
     * <p>
     * 클라이언트로부터 수정된 게시판 항목 정보를 담은 Board 객체를 전달받아,
     * 해당 게시판 항목의 정보를 업데이트합니다. 이 과정에서 해당 ID를 가진
     * 게시판 항목이 존재하는지 먼저 확인하며, 존재하지 않을 경우 에러 메시지를 반환합니다.
     * 성공적으로 수정되었을 경우, true 값을 담은 ResponseEntity를 반환합니다.
     *
     * @param board 클라이언트로부터 받은 수정된 게시판 항목 정보
     * @return 수정 성공 여부를 나타내는 Boolean 값을 포함한 ResponseEntity 객체
     */
    ResponseEntity<Boolean> modifyBoard(Board board);

    /**
     * 특정 게시판 항목을 삭제합니다.
     * <p>
     * 클라이언트로부터 삭제할 게시판 항목의 정보를 담은 Board 객체를 전달받아,
     * 해당 게시판 항목을 데이터베이스에서 삭제합니다. 삭제 과정에서 해당 ID를
     * 가진 게시판 항목이 존재하는지 먼저 확인하며, 존재하지 않을 경우 에러 메시지를 반환합니다.
     * 성공적으로 삭제되었을 경우, true 값을 담은 ResponseEntity를 반환합니다.
     *
     * @param board 클라이언트로부터 받은 삭제할 게시판 항목 정보
     * @return 삭제 성공 여부를 나타내는 Boolean 값을 포함한 ResponseEntity 객체
     */
    ResponseEntity<Boolean> deleteBoard(Board board);

}
