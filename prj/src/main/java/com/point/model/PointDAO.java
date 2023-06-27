package com.point.model;

import java.util.List;
import java.util.Map;


public interface PointDAO {
	
	// 메인 화면에서 Start! 버튼 누르면 member 테이블에 있는 point 차감
	int Disposition_Start(String id);
	
	// dispositionResult.jsp 에서 오류 발생시 포인트 복구
	int Disposition_Reset(String id);

	// 포인트 내역을 호출할 추상 메서드.
	List<PointDTO> pointList(Map<String, Object> pagingAndId);
	
	// 포인트 테이블에 내역을 저장할 추상 메서드.
	int pointInsert(Map<String, Object> pointInsert);
	
	// 포인트 테이블 총 인덱스 개수 구하는 추상 메서드.
	int memberPointCount(String id);
}
