package com.neusoft.oa.document.log.entity;

import java.util.Date;

import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DocumentLogEntity {
	private String id;// id
	private DocumentAttachmentEntity target;// 被操作附件编号
	private int operation;// 操作类型
	private String operationTime;// 操作时间
	private EmployeeEntity operator;// 操作者实体
	private String content;// 操作内容

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public DocumentAttachmentEntity getTarget() {
		return target;
	}

	public void setTarget(DocumentAttachmentEntity target) {
		this.target = target;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public EmployeeEntity getOperator() {
		return operator;
	}

	public void setOperator(EmployeeEntity operator_id) {
		this.operator = operator_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
