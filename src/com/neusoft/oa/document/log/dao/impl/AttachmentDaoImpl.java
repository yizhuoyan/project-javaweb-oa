package com.neusoft.oa.document.log.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.SQLGenerator;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.log.dao.AttachmentDao;
import com.neusoft.oa.document.log.entity.DocumentAttachmentEntity;


public class AttachmentDaoImpl extends TemplateDaoImpl<DocumentAttachmentEntity> implements AttachmentDao {

	protected AttachmentDaoImpl() {
		super("oa_attachment");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(DocumentAttachmentEntity t) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = SQLGenerator.generateInsertSql(this.tableName,
				"id,document_id,path,name,property,createUser_id,createTime,remark,flag,dept_id");

		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		int i = 1;
		ps.setString(i++, t.getId());
		ps.setString(i++, t.getDocumentId().getId());
		ps.setString(i++, t.getPath());
		ps.setString(i++, t.getName());
		ps.setString(i++, t.getProperty());
		ps.setString(i++, t.getCreateUserId().getId());
		ps.setObject(i++, t.getCreateTime());
		ps.setString(i++, t.getRemark());
		ps.setObject(i++, t.getFlag());
		ps.setString(i++, t.getDeptId().getId());

		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();

	}

	protected DocumentAttachmentEntity resultset2entity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
