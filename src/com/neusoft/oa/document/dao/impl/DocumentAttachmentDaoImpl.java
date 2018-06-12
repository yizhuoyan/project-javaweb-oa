package com.neusoft.oa.document.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.dao.DocumentAttachmentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;

public class DocumentAttachmentDaoImpl extends TemplateDaoImpl<DocumentAttachmentEntity>  implements DocumentAttachmentDao{

	protected DocumentAttachmentDaoImpl() {
		super("oa_attachment");
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public List<DocumentAttachmentEntity> selectAllByColumn(String ufield, Object value, String... orderbys) throws Exception {
		//1获取连接
				Connection conn= DBUtil.getConnection();
				//2组装SQL语句
				StringBuilder sql=new StringBuilder();
				sql.append(" form ").append(" oa_attachment ").append(" where flag=0 ")
				.append(DocumentAttachmentEntity.FLAG_DELETED);
				// 2查询总记录语句对象
				PreparedStatement ps = conn.prepareStatement("select * "+sql);
				
				// 4执行语句对象并获取结果
				ResultSet rs = ps.executeQuery();
				// 5转换结果为实体
				List<DocumentAttachmentEntity> result = new LinkedList<>();
				while (rs.next()) {
				DocumentAttachmentEntity e = resultset2entity(rs);
				result.add(e);
				}
				return result;
	}



	@Override
	public int selectsByKey(String userId,String key, int pageNo, int pageSize, List<DocumentAttachmentEntity> pageData)throws Exception {
			// 1获取连接
			Connection connection = DBUtil.getConnection();
			//组装wheresql 
			StringBuilder whereSql = new StringBuilder();
			whereSql.append(" from ").append(" oa_attachment ").append(" where flag=")
					.append(DocumentAttachmentEntity.FLAG_DELETED);
			System.out.println(userId);
			whereSql.append(" and createUser_id= ? ");
			if (key != null) {
				whereSql.append(" and  ( document_id like ? or name like ? or createUser_id like ? or dept_id like ? )");
			}

			// 2查询总记录语句对象
			PreparedStatement ps = connection.prepareStatement("select count(*) "+whereSql);
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
			querySql.append(" limit ").append((pageNo-1)*pageSize).append(",")
			.append(pageSize);

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
				DocumentAttachmentEntity e = resultset2entity(rs);
				pageData.add(e);
			}
			return total;
		}

	@Override
	protected DocumentAttachmentEntity resultset2entity(ResultSet rs) throws Exception {
		DocumentAttachmentEntity e= new DocumentAttachmentEntity();
		e.setId(rs.getString("id"));
		
		String document= rs.getString("document_id");
		if(document!=null) {
			DocumentEntity de=new DocumentEntity();
			de.setId(document);
			e.setDocumentId(de);
		}
		
		e.setPath(rs.getString("path"));
		e.setName(rs.getString("name"));
		e.setProperty(rs.getString("property"));
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



	@Override
	public void insert(DocumentAttachmentEntity t) throws Exception {
		
	}
	

}
