package com.neusoft.oa.attendance.dto;

import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class PageFindResult {
	private int totalRows;
	private int totalPages=-1;
	private int pageNo;
	private int pageSize;
	private List rows;
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		if(totalPages==-1) {
			totalPages=ThisSystemUtil.countPage(totalRows, pageSize);
		}
		return totalPages;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public <T>List getRows() {
		return rows;
	}
	
}
