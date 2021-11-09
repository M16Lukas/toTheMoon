package com.theMoon.moon.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.theMoon.moon.vo.Community;

@Repository
public class CommunityDAO {
	
	@Autowired
	private SqlSession session;
	
	/**
	 * 検索した株式のコメント出力
	 * 
	 * @param symbol
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> printContent(String symbol){
		ArrayList<HashMap<String, Object>> lists = null;
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			lists = mapper.printContent(symbol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	/**
	 * コメント登録
	 * 
	 * @param community
	 * @return
	 */
	public int insertContent(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.insertContent(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * コメント削除
	 * 
	 * @param community
	 * @return
	 */
	public int removeContent(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.removeContent(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 *  コメント修正
	 * 
	 * @param community
	 * @return
	 */
	public int modifyContent(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.modifyContent(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * コメントの推薦ヒストリー確認
	 * 
	 * @param community
	 * @return
	 */
	public HashMap<String, String> checkContentLikeHistory(Community community){
		HashMap<String, String> map = null;
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			map = mapper.checkContentLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * ヒストリーに推薦履歴追加
	 * 
	 * @param community
	 * @return
	 */
	public int insertContentLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.insertContentLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * 推薦ヒストリーアップデート
	 * 
	 * @param community
	 * @return
	 */
	public int updateContentLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.updateContentLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * [そう思う]ボタン
	 * 
	 * @param community
	 * @return
	 */
	public int contentUp(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.contentUp(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * ヒストリーに非推薦履歴追加
	 * 
	 * @param community
	 * @return
	 */
	public int insertContentDisLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.insertContentDisLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * 非推薦ヒストリーアップデート
	 * 
	 * @param community
	 * @return
	 */
	public int updateContentDisLikeHistory(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.updateContentDisLikeHistory(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * [そう思わない]ボタン
	 * 
	 * @param community
	 * @return
	 */
	public int contentDown(Community community) {
		int cnt = -1;
		
		try {
			CommunityMapper mapper = session.getMapper(CommunityMapper.class);
			cnt = mapper.contentDown(community);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
