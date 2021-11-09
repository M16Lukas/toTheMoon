package com.theMoon.moon.vo;

import lombok.Data;

/**
 * 株式ニュース　VO
 * 
 * @author ipark
 *
 */

@Data
public class FeedMessage {
	private String title; 		// 題目
	private String link; 		// リンク
	private String guid; 		// ID値
	private String pubDate; 	// 作成時間
	private String description; // 内容 
	private String source; 

}
