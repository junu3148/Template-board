package com.board.www.comm.pageing;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
public class Criteria {

    private int pageNum;
    private int amount; // 현재 페이지, 페이지 당 보여질 게시물 갯수
    private int showStat;
    private String keyword;
    private String type; // 검색 키워드, 검색 타입
    // private String[] typeArr; //검색 타입 배열

    // 기본 생성자 -> 기본세팅 : pageNum=1, amount=10
    public Criteria() {
        this(1, 10);
    }


    // 생성자 -> 원하는 pageNum, 원하는 amount
    public Criteria(int pageNum, int amount) {
        super();
        this.pageNum = pageNum;
        this.amount = amount;
    }


    // Criteria 인스턴스에서 Pageable 객체 생성
    public Pageable toPageable() {
        return PageRequest.of(this.pageNum - 1, this.amount, Sort.Direction.DESC, "boardKey"); // 예시로 "id" 기준으로 내림차순 정렬
    }


}