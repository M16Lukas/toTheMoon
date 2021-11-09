package com.theMoon.moon.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * 株式ニュース　VO
 * 
 * @author ipark
 *
 */

@Getter
public class Feed {

	private String title;
	private String link;
	private String description;	//　内容
	private String language;
	private String copyright;	
	private String pubDate;		//　作成時間
	
	private List<FeedMessage> entries = new ArrayList<FeedMessage>();

	public Feed(String title, String link, String description, String language, String copyright, String pubDate) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.pubDate = pubDate;
	}
	
	public List<FeedMessage> getMessages() {
		return entries;
	}

	@Override
	public String toString() {
		return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
	}
	
	
}
