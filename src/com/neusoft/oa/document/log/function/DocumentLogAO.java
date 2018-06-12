package com.neusoft.oa.document.log.function;

import java.util.Date;


public class DocumentLogAO {
	private String target;//被操作附件编号
	private String operation;//操作类型
	private String beginoperationTime;//操作开始时间
	private String endoperationTime;//操作结束时间
	private String operatorId;//操作者id

	public String getBeginoperationTime() {
		return beginoperationTime;
	}
	public void setBeginoperationTime(String beginoperationTime) {
		this.beginoperationTime = beginoperationTime;
	}
	public String getEndoperationTime() {
		return endoperationTime;
	}
	public void setEndoperationTime(String endoperationTime) {
		this.endoperationTime = endoperationTime;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
