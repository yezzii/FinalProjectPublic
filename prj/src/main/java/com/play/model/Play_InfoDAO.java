package com.play.model;

import java.util.List;

import com.community.model.PageDTO;

public interface Play_InfoDAO {
	//전체 플레이 리스트 메서드
	List<Play_InfoDTO> getPlaySearchList(List<String> locations,List<String> group, List<String> price);
	List<Play_InfoDTO> getDistanceSearchList(List<String> distances);
	
	List<Play_InfoDTO> getPlayList();
	//검색 ajax
	void loccheck();
	
	void groupcheck();
	
	void groupprice();
	
	List<Play_InfoDTO> PlayList_loc(List<String> locations);
	
	List<Play_InfoDTO> PlayList_group(List<String> group);

	List<Play_InfoDTO> PlayList_price(List<String> price);
	
	// 조회수
	void readCount(int playIndex);

	Play_InfoDTO playCont(int playIndex);

	String getPlayViewCount(int playIndex);
	
	
	 //Random
	List<Play_InfoDTO> getRandomList();

	List<Play_InfoDTO> getRandomOutList();

	List<Play_InfoDTO> getRandomInList();

	

	//좋아요 count
	void play_likecountmin(int play_it);

	void play_likecountupdate(int play_it);
	
	int getPlayLikeCount(int play_it);
	//종아요 한 리스트
	List<Integer> getPlayLikeList(String id);
	
	// 검색 총 갯수
	int searchPlayCount(String keyword);
	//검색 리스트
	List<Play_InfoDTO> searchPlayList(String keyword);
	
	// 업데이트 플레이
	int updatePlayInfo(int star, int play_index);
	
	// 별점 나누기 
	int updatePlaycount(int play_index);
	//별점 지우기
	int deletePlayInfo(int starnum, int indexnum);
	
	//메인 실외를 좋아하는 당신 best
	List<Play_InfoDTO> mainOutSideList();
	//메인 실내를 좋아하는 당신 best
	List<Play_InfoDTO> mainInSideList();
	
	
	
}
