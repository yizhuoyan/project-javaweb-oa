package com.neusoft.oa.document.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dao.SQLGenerator;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;

public class AttachmentDaoImpl extends TemplateDaoImpl<DocumentAttachmentEntity> implements AttachmentDao {

	protected AttachmentDaoImpl() {
		super("oa_attachment");
	}
	@Override
	public List<DocumentAttachmentEntity> selectAllByColumn(String field, Object value,String... orderbys) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName)
		.append(" where  flag!=0 and ").append(field);
		if(value==null) {
			sql.append(" is null ");
		}else {
			sql.append("=?");
		}
		if(orderbys.length>0) {
			sql.append(" order by ");
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length()-1, ' ');
		}
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		if(value!=null) {
			ps.setObject(1, value);
		}
		ResultSet rs = ps.executeQuery();
		List<DocumentAttachmentEntity> result=new LinkedList<>();
		while (rs.next()) {
			result.add(resultset2entity(rs));
		}
		return result;
	}

	@Override
	public void insert(DocumentAttachmentEntity t) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = SQLGenerator.generateInsertSql(this.tableName,
				"id,document_id,path,name,property,createUser_id,createTime,remark,flag,dept_id,type,size");

		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		int i = 1;
		ps.setString(i++, t.getId());
		ps.setString(i++, t.getDocumentId().getId());
		ps.setString(i++, t.getPath());
		ps.setString(i++, t.getName());
		ps.setString(i++, t.getProperty());
		ps.setString(i++, t.getCreateUserId().getId());
		ps.setObject(i++, this.instant2timestamp(t.getCreateTime()));
		ps.setString(i++, t.getRemark());
		ps.setObject(i++, t.getFlag());
		ps.setString(i++, t.getDeptId().getId());
		ps.setString(i++, t.getType());
		ps.setObject(i++, t.getSize());

		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();

	}

	protected DocumentAttachmentEntity resultset2entity(ResultSet rs) throws Exception {
		DocumentAttachmentEntity d=new DocumentAttachmentEntity();
		d.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		
		String creatUserId = rs.getString("createUser_id");
		EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
		d.setCreateUserId(edao.select("id", creatUserId));
		
		String deptId = rs.getString("dept_id");
		DepartmentDao ddao = DaoFactory.getDao(DepartmentDao.class);
		d.setDeptId(ddao.select("id", deptId));
		
		String documentId=rs.getString("document_id");
		DocumentDao doc=DaoFactory.getDao(DocumentDao.class);
		d.setDocumentId(doc.select("id", documentId));
		
		d.setFlag(rs.getInt("flag"));
		d.setId(rs.getString("id"));
		d.setName(rs.getString("name"));
		d.setPath(rs.getString("path"));
		d.setProperty(rs.getString("property"));
		d.setRemark(rs.getString("remark"));
		d.setSize(rs.getLong("size"));
		d.setType(rs.getString("type"));
		
		return d;
	}

}
