package com.dto;

import java.util.List;

public class PageDTO {

	//4가지 정보 선언
	List<BoardDTO> list; //한 페이지에 에 보여줄 list
	int perPage = 3; //페이지 당 출력될 레코드
	int totalCount; //전체 레코드 수
	int curPage; //현재 페이지 번호

	//검색용
	String searchName;
	String searchValue;

	//=>PageDTO에 총 6가지 정보 저장함
	
	
	//getter, setter  (생성자X)
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public List<BoardDTO> getList() {
		return list;
	}

	public void setList(List<BoardDTO> list) {
		this.list = list;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

}
