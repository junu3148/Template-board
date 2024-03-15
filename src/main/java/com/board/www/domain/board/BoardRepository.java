package com.board.www.domain.board;

import com.board.www.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

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
