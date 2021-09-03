package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.theMoon.moon.vo.Community;

public interface CommunityMapper {
	
	/**
	 * 해당 주식의 댓글 가져오기 
	 * 
	 * @param symbol
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> printContent(String symbol);
	
	/**
	 * 댓글 등록
	 * 
	 * @param community
	 * @return
	 */
	public int insertContent(Community community);
	
	/**
	 * 댓글 삭제
	 * 
	 * @param community
	 * @return
	 */
	public int removeContent(Community community);
	
	/**
	 *  댓글 수정
	 * 
	 * @param community
	 * @return
	 */
	public int modifyContent(Community community);
	
	/**
	 * 댓글 추천 여부 확인
	 * 
	 * @param community
	 * @return
	 */
	public HashMap<String, String> checkContentLikeHistory(Community community);
	
	/**
	 * 댓글 추천 이력 추가
	 * 
	 * @param community
	 * @return
	 */
	public int insertContentLikeHistory(Community community);
	
	/**
	 * 댓글 추천 이력 수정
	 * 
	 * @param community
	 * @return
	 */
	public int updateContentLikeHistory(Community community);
	
	/**
	 * 댓글 추천
	 * 
	 * @param community
	 * @return
	 */
	public int contentUp(Community community);
	
	/**
	 * 댓글 비추천 이력 추가
	 * 
	 * @param community
	 * @return
	 */
	public int insertContentDisLikeHistory(Community community);
	
	/**
	 * 댓글 비추천 이력 수정
	 * 
	 * @param community
	 * @return
	 */
	public int updateContentDisLikeHistory(Community community);

	/**
	 * 댓글 비추천
	 * 
	 * @param community
	 * @return
	 */
	public int contentDown(Community community);
}
