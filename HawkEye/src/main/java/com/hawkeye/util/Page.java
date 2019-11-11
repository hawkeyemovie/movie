package com.hawkeye.util;

import java.util.List;

public class Page<T> {
	private int totalPageCount;//总页数
	private int pageSize=5;//每页显示的数据
	private int totalCount;//总数据
	private int currentPageNo=1;//当前的页码
	private List<T> list;//每页数据集合
	private int typeid;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount>0) {
			this.totalCount=totalCount;
			totalPageCount=this.totalCount%pageSize==0?
							(this.totalCount/pageSize):(this.totalCount/pageSize)+1;
		}else{
			totalPageCount = 1;
		}
	}
	public int getcurrentPageNo() {
		return currentPageNo;
	}
	public void setcurrentPageNo(int currentPageNo) {
		if(currentPageNo<1){
			this.currentPageNo=1;
		}else if(currentPageNo>this.totalPageCount){
			this.currentPageNo = this.totalPageCount;
		}else{
			this.currentPageNo=currentPageNo;
		}
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
