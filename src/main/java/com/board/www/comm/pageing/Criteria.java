package com.board.www.comm.pageing;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Criteria {
    private int pageNum, amount; // 현재 페이지, 페이지 당 보여질 게시물 갯수
    private int showStat;
    private int keyword;
    private String keyword2;
    private String type; // 검색 키워드, 검색 타입
    // private String[] typeArr; //검색 타입 배열

    // 기본 생성자 -> 기본세팅 : pageNum=1, amount=10
    public Criteria() {
        this(1, 10, 1);
    }

    public Criteria(int pageNum, int amount, String keyword2) {
        super();
        this.pageNum = pageNum;
        this.amount = amount;
        this.keyword2 = keyword2;
    }

    // 생성자 -> 원하는 pageNum, 원하는 amount
    public Criteria(int pageNum, int amount, int keyword) {
        super();
        this.pageNum = pageNum;
        this.amount = amount;
        this.keyword = keyword;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getShowStat() {
        return showStat;
    }

    public void setShowStat(int showStat) {
        this.showStat = showStat;
    }

    public int getKeyword() {
        return keyword;
    }

    public void setKeyword(int keyword) {
        this.keyword = keyword;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", showStat=" + showStat + ", keyword=" + keyword
                + ", keyword2=" + keyword2 + ", type=" + type + "]";
    }

    // Criteria 인스턴스에서 Pageable 객체 생성
    public Pageable toPageable() {
        return PageRequest.of(this.pageNum - 1, this.amount, Sort.Direction.DESC, "id"); // 예시로 "id" 기준으로 내림차순 정렬
    }

}