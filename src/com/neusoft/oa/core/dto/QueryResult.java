package com.neusoft.oa.core.dto;

import java.util.ArrayList;
import java.util.List;

public class QueryResult<T> {
	//总行数
	protected int totalRows;
	//数据
	protected List<T> rows;
	
	/**
	 * 是否查询到数据
	 * @return
	 */
	public boolean isFound() {
		return this.totalRows>0&&this.rows!=null;
	}
	public boolean isNotFound() {
		return !isFound();
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public <R>QueryResult<R> mapRows(java.util.function.Function<T, R> mapper){
		List<T> rows=this.rows;
		List<R> mapRows=new ArrayList<>(rows.size());
		for(T r:rows) {
			mapRows.add(mapper.apply(r));
		}
		QueryResult<R> result=new QueryResult<>();
		result.setTotalRows(this.totalRows);
		result.setRows(mapRows);
		return result;
	}
	
	
	
}
