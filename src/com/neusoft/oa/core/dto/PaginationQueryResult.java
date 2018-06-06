/**
 * 
 */
package com.neusoft.oa.core.dto;

/**
 * @author Administrator
 *
 */
public class PaginationQueryResult<T> extends QueryResult<T>{
	/**总页数*/
	private int totalPages=-1;
	private int pageSize;
	private int pageNo;
	/**
	 * @return the totalPage
	 */
	public int getTotalPages() {
		if(this.totalPages==-1) {
			this.totalPages=this.totalRows/this.pageSize;
			if(this.totalRows%this.pageSize!=0) {
				this.totalPages+=1;
			}
		}
		return totalPages;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
}
