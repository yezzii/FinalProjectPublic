package com.play.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.model.PageDTO;

@Repository
public class Play_InfoDAOImpl implements Play_InfoDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Play_InfoDTO> getPlayList() {
		return this.sqlSession.selectList("all");
	}
	
	@Override
	public List<Play_InfoDTO> getPlaySearchList(List<String> locations, List<String> groups, List<String> prices) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (locations != null) {
			paramMap.put("locations", locations);

		}

		if (groups != null) {
			paramMap.put("groups", groups);

		}

		if (prices != null) {
			paramMap.put("prices", prices);

		}

		System.out.println("파람맵1" + paramMap);
		return this.sqlSession.selectList("getPlaySearchList", paramMap);

	}

	@Override
	public List<Play_InfoDTO> getDistanceSearchList(List<String> distances) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (distances != null) {
			paramMap.put("distances", distances);
		}

		System.out.println("파람맵2" + paramMap);
		return this.sqlSession.selectList("getPlayDistanceSearchList", paramMap);
	}

	// 검색 메서드
	@Override
	public void loccheck() {
	}

	@Override
	public void groupcheck() {

	}

	@Override
	public void groupprice() {
	}

	@Override
	public List<Play_InfoDTO> PlayList_loc(List<String> locations) {
		return this.sqlSession.selectList("PlayList_loc", locations);
	}

	@Override
	public List<Play_InfoDTO> PlayList_group(List<String> group) {
		return this.sqlSession.selectList("PlayList_group", group);
	}

	@Override
	public List<Play_InfoDTO> PlayList_price(List<String> price) {
		return this.sqlSession.selectList("PlayList_price", price);
	}

	@Override
	public void readCount(int playIndex) {
		this.sqlSession.update("read", playIndex);
	} 

	@Override
	public Play_InfoDTO playCont(int playIndex) {
		return this.sqlSession.selectOne("PlayCont", playIndex);
	}

	@Override
	public String getPlayViewCount(int playIndex) {
		return String.valueOf(this.sqlSession.selectOne("view", playIndex));
	}

	public List<Play_InfoDTO> getRandomList() {
		return this.sqlSession.selectList("RandomPlay");

	}

	@Override
	public List<Play_InfoDTO> getRandomOutList() {
		return this.sqlSession.selectList("RandomPlayOutside");

	}

	@Override
	public List<Play_InfoDTO> getRandomInList() {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("RandomPlayInside");
	}

	@Override
	public void play_likecountmin(int play_it) {
		this.sqlSession.update("countmin", play_it);

	}

	@Override
	public void play_likecountupdate(int play_it) {

		this.sqlSession.update("countplus", play_it);
	}

	@Override
	public int getPlayLikeCount(int play_it) {
		return this.sqlSession.selectOne("likecount", play_it);
	}

	@Override
	public List<Integer> getPlayLikeList(String id) {
		return this.sqlSession.selectList("likelist", id);
	}

	@Override
	public int searchPlayCount(String keyword) {
		return this.sqlSession.selectOne("searchPlay", keyword);
	}

	@Override
	public List<Play_InfoDTO> searchPlayList(String keyword) {
		return this.sqlSession.selectList("searchplaylist",keyword);
	}

	@Override
	public int updatePlayInfo(int star, int play_index) {
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("star", star);
		info.put("play_index", play_index);
		return this.sqlSession.update("updateinfo", info);
	}

	@Override
	public int updatePlaycount(int play_index) {
		return this.sqlSession.update("star",play_index);
	}

	@Override
	public int deletePlayInfo(int starnum, int indexnum) {
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("star", starnum);
		info.put("play_index", indexnum);
		return this.sqlSession.update("deleteinfo", info);
	}

	@Override
	public List<Play_InfoDTO> mainOutSideList() {
		return this.sqlSession.selectList("outSideall");
	}

	@Override
	public List<Play_InfoDTO> mainInSideList() {
		return this.sqlSession.selectList("inSideall");
	}





	

}
