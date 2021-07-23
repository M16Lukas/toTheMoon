package com.theMoon.moon.vo;

import lombok.Data;

@Data
public class FeedMessage {
	private String title; // 제목 title
	private String link; // 링크 link  
	private String guid; // 아이디값 
	private String pubDate; // 작성시간 pubDate
	private String description; // 내용 description 
	private String source; 

}
