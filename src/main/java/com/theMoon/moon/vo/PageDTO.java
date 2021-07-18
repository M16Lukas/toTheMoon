package com.theMoon.moon.vo;

import lombok.Data;

@Data
public class PageDTO {
	private int total;			// 전체 데이터 수
	
	/* Table data */
	private int startDataIdxNum;	// 시작 데이터 인덱스 번호(0 부터 시작)
	private int endDataIdxNum;		// 끝 데이터 인덱스 번호
	
	/* Page */
	private int countPerPage;	// 한 페이지 당 보여줄 데이터 수
	private int currentPage;	// 현재 페이지(1부터 시작)
	private int totalPageCount;	// 전체 페이지 수(마지막 페이지 번호)

	/* Group */
	private final int pagePerGroup = 10;	// 그룹 당 페이지 수
	private int startPageGroup; 			// 현재 그룹의 시작 페이지
	private int endPageGroup; 				// 현재 그룹의 마지막 페이지
	private int currentGroup;				// 현재 그룹(0 부터 시작)
	
	private boolean prev, next;
	
	
	/*
	 * Constructors
	 * */
	
	public PageDTO() {}
	
	public PageDTO(int countPerPage, int currentPage, int total) {
		this.total = total;
		
		// 전체 페이지 수(마지막 페이지 번호)
		this.totalPageCount = (total + countPerPage - 1) / countPerPage;
		
		// 현재 페이지가 1 보다 작으면 1 페이지로 이동
		if (currentPage < 1) currentPage = 1;
		
		// 현재 페이지가 마지막 페이지보다 크면 마지막 페이지로 이동
		if (currentPage > totalPageCount) currentPage = totalPageCount;
		
		this.currentPage = currentPage;
		
		// 현재 페이지 시작 데이터 인덱스 번호(0 부터 시작)
		this.startDataIdxNum = (currentPage - 1) * countPerPage;
				
		// 현재 페이지 마지막 데이터 인덱스 번호
		endDataIdxNum = startDataIdxNum + countPerPage - 1;
		
		// 현재 페이지 마지막 데이터 인덱스 번호가 전체 데이터 마지막 인덱스 보다 크면 total - 1로 처리
		this.endDataIdxNum = (total - 1) < endDataIdxNum ? (total - 1) : endDataIdxNum;
		
		// 현재 그룹(0 부터 시작)
		this.currentGroup = (currentPage - 1) / pagePerGroup;
		
		// 현재 그룹의 시작 페이지
		startPageGroup = (pagePerGroup * currentGroup) + 1;
		
		// 현재 그룹의 첫 페이지가 1 보다 작으면 1로 처리
		this.startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		
		// 현재 그룹의 마지막 페이지
		endPageGroup = startPageGroup + pagePerGroup - 1;
		
		// 현재 그룹의 마지막 페이지가 전체 페이지 수보다 작으면 전체 페이지 수로 처리
		this.endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
		
		// 이전, 다음 그룹으로 이동
		this.prev = this.startPageGroup > 1;
		this.next = this.endPageGroup < totalPageCount;
	}
	
}
