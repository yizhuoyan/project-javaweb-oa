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
import com.neusoft.oa.document.dao.AttachmentDao;
import com.neusoft.oa.document.entity.DocumentAttachmentEntity;
import com.neusoft.oa.document.entity.DocumentEntity;

public class AttachmentDaoImpl extends TemplateDaoImpl<DocumentAttachmentEntity>  implements AttachmentDao{

	protected AttachmentDaoImpl() {
		super("oa_attachment");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(DocumentAttachmentEntity t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String ufield, Object value) throws Exception {
		// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 2创建sql语句对象
				StringBuilder sql = new StringBuilder();
				sql.append("delete from  ").append(tableName).append(" where ");
				sql.append(ufield).append(" =? ");
				PreparedStatement ps = connection.prepareStatement(sql.toString());
				// 3传入参数并执行语句对象
				ps.setObject(1, value);
				// 4获取执行结果（如果是查询，则转化为实体）
				ps.executeUpdate();
		
	}

	@Override
	public void update(Serializable id, Map<String, Object> columns) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Serializable id, String column, Object value) throws Exception {
		
		// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 2创建sql语句对象
				StringBuilder sql = new StringBuilder();
				sql.append("update ").append(" oa_attachment ").append(" set ");
				sql.append(column).append("=? ");
				sql.append(" where id=?");
				PreparedStatement ps = connection.prepareStatement(sql.toString());

				// 3传入参数并执行语句对象
				ps.setObject(1, value);
				ps.setObject(2, id);
				// 4获取执行结果（如果是查询，则转化为实体）
				ps.executeUpdate();
	}
	


	@Override
	public DocumentAttachmentEntity select(String ufield, Object value) throws Exception {
		//1获取连接
		Connection conn= DBUtil.getConnection();
		//2组装SQL语句
		StringBuilder sql=new StringBuilder();
		sql.append(" form ").append(" oa_attachment ").append(" where flag=1 ");
		return null;
	}

	@Override
	public List<DocumentAttachmentEntity> selectAllByColumn(String ufield, Object value, String... orderbys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DocumentAttachmentEntity> selectAll(String... orderbys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<DocumentAttachmentEntity> pageData)throws Exception {
			// 1获取连接
			Connection connection = DBUtil.getConnection();
			//组装wheresql 
			StringBuilder whereSql = new StringBuilder();
			whereSql.append(" from ").append(" oa_attachment ").append(" where flag=")
					.append(DocumentAttachmentEntity.FLAG_DELETED);
			if (key != null) {
				whereSql.append(" and  ( document_id like ? or name like ? or createUser_id like ? or dept_id like ? )");
			}

			// 2查询总记录语句对象
			PreparedStatement ps = connection.prepareStatement("select count(*) "+whereSql);
			// 3传入参数
			if (key != null) {
				int i = 1;
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
			if (key != null) {
				int i = 1;
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
		e.setDocumentId(rs.getString("document_id"));
		
		e.setPath(rs.getString("path"));
		e.setName(rs.getString("name"));
		e.setProperty(rs.getString("property"));
		e.setCreateUserId(rs.getString("createUser_id"));
		
		e.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		e.setRemark(rs.getString("remark"));
		e.setDeptId(rs.getString("dept_id"));
		return e;
	}
	

}
