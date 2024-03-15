package com.board.www.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardKey;

    @Column(length = 255, unique = true, nullable = false)
    private String boardTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String boardContent;

    @Column(nullable = false)
    private LocalDateTime boardDate = LocalDateTime.now();

    @Column(nullable = false)
    private Integer viewCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BoardStatus boardStatus = BoardStatus.ACTIVE;


}

enum BoardStatus {
    ACTIVE, INACTIVE, DELETED
}