package com.neusoft.oa.document.log.dao;

import com.neusoft.oa.organization.entity.EmployeeEntity;

public class content {
	String id;// id
	String target;// 被操作附件编号
	int operation;// 操作类型
	String operationTime;// 操作时间
	String beginTime;// 操作开始时间
	String endTime;// 操作结束时间
	EmployeeEntity operator;// 操作者id
	String content;// 操作内容

	// 操作 1放入回收站3创建4下载5还原6彻底删除
	String title = operationTime + "," + operator.getName() + "将" + target;
	String putInRecycle = title + "放入回收站";
	String create = title + "创建";
	String download = title + "下载";
	String reduction = title + "从回收站还原";
	String delete = title + "从回收站彻底删除";

}
