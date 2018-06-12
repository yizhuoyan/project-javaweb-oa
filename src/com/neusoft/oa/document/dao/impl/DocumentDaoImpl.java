package com.neusoft.oa.document.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;

public class DocumentDaoImpl extends TemplateDaoImpl<DocumentEntity> implements DocumentDao {

	protected DocumentDaoImpl() {
		super("oa_document");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(DocumentEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectsByKey(String userId,String key, int pageNo, int pageSize, List<DocumentEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from ").append(" oa_document ").append(" where flag= ").append(DocumentEntity.FLAG_DELETED);
		whereSql.append(" and createUser_id= ? ");
		if (key != null) {
			whereSql.append(" and  ( id like ? or name like ? or createUser_id like ? or dept_id like ? )");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement("select count(*) " + whereSql);
		// 3传入参数
		ps.setString(1, userId);
		if (key != null) {
			int i = 2;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		int total = 0;
		if (rs.next()) {
			total = rs.getInt(1);
		}
		// 没有满足查询查询条件的数据，直接返回
		if (total == 0) {
			return total;
		}
		// 执行分页查询
		StringBuilder querySql = new StringBuilder();
		querySql.append(" select * ").append(whereSql);
		querySql.append(" order by id desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		ps.setString(1, userId);
		if (key != null) {
			int i = 2;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			DocumentEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}

	

	@Override
	protected DocumentEntity resultset2entity(ResultSet rs) throws Exception {
		DocumentEntity e= new DocumentEntity();
		e.setId(rs.getString("id"));
		e.setProperty(rs.getString("property"));
		e.setPath(rs.getString("path"));
		e.setName(rs.getString("name"));
		String createUserId= rs.getString("createUser_id");
		if(createUserId!=null) {
			SysUserEntity user=new SysUserEntity();
			user.setId(createUserId);
			e.setCreateUserId(user);
		}
		e.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		e.setRemark(rs.getString("remark"));
		e.setDeptId(rs.getString("dept_id"));
		
		return e;
	}
}
