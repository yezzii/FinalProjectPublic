package com.play.model;

import org.springframework.stereotype.Repository;

import lombok.Data;
@Repository
@Data
public class Play_PageDTO {
	//페이징 처리 관련 멤버 선언
	private int page;
	private int rowsize;
	private int totalRecord;
	private int startNo;
	private int endNo;
	private int startBlock;
	private int endBlock;
	private int allPage;
	private int block = 5;
	
	public Play_PageDTO() {}
	
	public Play_PageDTO(int page,int rowsize,int totalRecord) {
		
		this.page = page;
		this.rowsize = rowsize;
		this.totalRecord = totalRecord;
		
		//해당 페이지에서 시작 번호
		this.startNo = (this.page * this.rowsize) - (this.rowsize -1);
		
		//해당 페이지에서 끝 번호
		this.endNo = (this.page * this.rowsize);
		
		//해당 페이지에서 시작 블럭
		this.startBlock = (((this.page -1)/this.block)* this.block) + 1;
		
		//해당 페이지에서 마지막 블럭
		endBlock = (((this.page-1)/this.block)*this.block)+ this.block;
		
		//전체 페이지 수 얻어오는 과정
		this.allPage = (int)Math.ceil(this.totalRecord/(double)this.rowsize);
		
		if(this.endBlock > this.allPage) {
			this.endBlock = this.allPage;
		
		}
		
	}
}


