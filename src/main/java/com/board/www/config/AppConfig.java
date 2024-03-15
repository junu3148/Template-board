package com.board.www.config;

import com.board.www.domain.board.BoardRepository;
import com.board.www.domain.board.BoardService;
import com.board.www.domain.board.BoardServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public BoardService boardService(BoardRepository boardRepository){

        return new BoardServiceImpl(boardRepository);
    }

}
