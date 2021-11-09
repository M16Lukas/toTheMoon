package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.theMoon.moon.vo.Community;

public interface CommunityMapper {
	
	/**
	 * 検索した株式のコメント出力
	 * 
	 * @param symbol
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> printContent(String symbol);
	
	/**
	 * コメント登録
	 * 
	 * @param community
	 * @return
	 */
	public int insertContent(Community community);
	
	/**
	 * コメント削除
	 * 
	 * @param community
	 * @return
	 */
	public int removeContent(Community community);
	
	/**
	 *  コメント修正
	 * 
	 * @param community
	 * @return
	 */
	public int modifyContent(Community community);
	
	/**
	 * コメントの推薦ヒストリー確認
	 * 
	 * @param community
	 * @return
	 */
	public HashMap<String, String> checkContentLikeHistory(Community community);
	
	/**
	 * ヒストリーに推薦履歴追加
	 * 
	 * @param community
	 * @return
	 */
	public int insertContentLikeHistory(Community community);
	
	/**
	 * 推薦ヒストリーアップデート
	 * 
	 * @param community
	 * @return
	 */
	public int updateContentLikeHistory(Community community);
	
	/**
	 * [そう思う]ボタン
	 * 
	 * @param community
	 * @return
	 */
	public int contentUp(Community community);
	
	/**
	 * ヒストリーに非推薦履歴追加
	 * 
	 * @param community
	 * @return
	 */
	public int insertContentDisLikeHistory(Community community);
	
	/**
	 * 非推薦ヒストリーアップデート
	 * 
	 * @param community
	 * @return
	 */
	public int updateContentDisLikeHistory(Community community);

	/**
	 * [そう思わない]ボタン
	 * 
	 * @param community
	 * @return
	 */
	public int contentDown(Community community);
}
