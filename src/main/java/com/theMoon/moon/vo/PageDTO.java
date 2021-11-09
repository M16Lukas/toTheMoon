package com.theMoon.moon.vo;

import lombok.Data;

/**
 * 時系列データのページング　DTO
 * 
 * @author ipark
 *
 */

@Data
public class PageDTO {
	private int total;						// 全体データの数
	
	/* Table data */
	private int startDataIdxNum;			// スタートデータのインデックス番号（0からスタート）
	private int endDataIdxNum;				// エンドデータのインデックス番号
	
	/* Page */
	private int countPerPage;				// ページあたり表記するデータ数
	private int currentPage;				// 現在ページ（1からスタート）
	private int totalPageCount;				// 全体ページ数（最後のページ番号）

	/* Group */
	private final int pagePerGroup = 10;	// グループあたりページ数
	private int startPageGroup; 			// 現在グループのスタートページ
	private int endPageGroup; 				// 現在グループの最後のページ
	private int currentGroup;				// 現在グループ（0からスタート）
	
	private boolean prev, next;
	
	
	/*
	 * Constructors
	 * */
	
	public PageDTO() {}
	
	public PageDTO(int countPerPage, int currentPage, int total) {
		this.total = total;
		
		// 全体ページ数（最後のページ番号）
		this.totalPageCount = (total + countPerPage - 1) / countPerPage;
		
		// 現在ページが1より小さい場合、1ページに移動
		if (currentPage < 1) currentPage = 1;
		
		// 現在ページが最後のページより大きい場合、最後のページに移動
		if (currentPage > totalPageCount) currentPage = totalPageCount;
		this.currentPage = currentPage;
		
		// 現在ページのスタートデータのインデックス番号（0からスタート）
		this.startDataIdxNum = (currentPage - 1) * countPerPage;
				
		// 現在ページのエンドデータのインデックス番号
		endDataIdxNum = startDataIdxNum + countPerPage - 1;
		
		// 現在ページのエンドデータのインデックス番号が全体データの最後のインデックスより大きい場合
		this.endDataIdxNum = (total - 1) < endDataIdxNum ? (total - 1) : endDataIdxNum;
		
		// 現在グループ（0からスタート）
		this.currentGroup = (currentPage - 1) / pagePerGroup;
		
		// 現在グループのスタートページ
		startPageGroup = (pagePerGroup * currentGroup) + 1;
		
		// 現在グループのスタートページが1より小さい場合
		this.startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		
		// 現在グループの最後のページ
		endPageGroup = startPageGroup + pagePerGroup - 1;
		
		// 現在グループの最後のページが全体ページの数より小さい場合
		this.endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
		
		// 以前グループに移動
		this.prev = this.startPageGroup > 1;
		//　次のグループに移動
		this.next = this.endPageGroup < totalPageCount;
	}
	
}
