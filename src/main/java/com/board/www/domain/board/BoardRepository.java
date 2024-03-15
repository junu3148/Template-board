package com.board.www.domain.board;

import com.board.www.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    /**
     * 특정 상태의 게시글을 페이징 처리하여 검색합니다.
     * <p>
     * 이 메서드는 게시글의 상태를 기준으로 모든 게시글을 검색하되, Pageable 인터페이스를 통해 페이징 처리를 지원합니다.
     * boardStatus 파라미터를 통해 원하는 게시글의 상태(예: "ACTIVE", "INACTIVE" 등)를 지정할 수 있으며,
     * pageable 파라미터를 통해 반환되는 게시글의 수와 페이지 번호를 조정할 수 있습니다.
     * 이 메서드는 주로 게시판의 글을 상태에 따라 필터링하고, 결과를 페이지별로 나누어 보여줄 때 사용됩니다.
     *
     * @param boardStatus 검색할 게시글의 상태
     * @param pageable 페이징 정보(페이지 번호, 페이지 당 항목 수 등)
     * @return 주어진 상태의 게시글 페이지
     */
    Page<Board> findByBoardStatus(String boardStatus, Pageable pageable);

    /**
     * 특정 키워드와 상태를 기준으로 게시글을 검색하고 페이징 처리하여 반환합니다.
     * <p>
     * 이 메서드는 게시글의 제목 또는 내용에 주어진 키워드가 포함되어 있으며, 특정 상태(boardStatus)인 게시글을 검색합니다.
     * JPQL을 사용하여, 제목 또는 내용 중 하나라도 키워드를 포함하는 게시글을 찾되, boardStatus 조건도 만족해야 합니다.
     * pageable 파라미터를 통해 결과 게시글의 페이징 처리를 지원하여, 클라이언트에게는 검색 결과의 일부분만 제공할 수 있게 합니다.
     * 이 메서드는 게시글을 검색할 때 사용자가 입력한 키워드를 기준으로 관련 게시글을 찾아주는 검색 기능에 사용됩니다.
     *
     * @param keyword 검색할 키워드 (게시글 제목 또는 내용에 포함)
     * @param boardStatus 검색할 게시글의 상태
     * @param pageable 페이징 정보
     * @return 조건에 맞는 게시글 페이지
     */
    Page<Board> findByKeywordAndBoardStatus(String keyword, String boardStatus, Pageable pageable);

    /**
     * 지정된 게시글의 조회수를 1 증가시킵니다.
     * <p>
     * 이 메서드는 특정 게시글의 고유 식별자인 boardKey를 받아 해당 게시글의 조회수(viewCount)를 1 증가시킵니다.
     * 조회수 증가는 데이터베이스 내에서 직접 수행되며, 이 메서드는 조회수를 업데이트하는 데 사용됩니다.
     * JPQL(Java Persistence Query Language)을 사용하여 업데이트 쿼리를 실행합니다.
     *
     * @param boardKey 조회수를 증가시킬 게시글의 ID
     */
    @Modifying
    @Query("UPDATE Board b SET b.viewCount = b.viewCount + 1 WHERE b.boardKey = :boardKey")
    void incrementViewCount(@Param("boardKey") Long boardKey);
}
