package com.neusoft.oa.document.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.dao.RecycleDao;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.entity.RecycleEntity;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class RecycleDaoImpl extends TemplateDaoImpl<RecycleEntity> implements RecycleDao {

	protected RecycleDaoImpl(String tableName) {
		super(tableName);
		// TODO Auto-generated constructor stub
	}

	public int selectsByKey(String key, int pageNo, int pageSize,  List<RecycleEntity> pageData)throws Exception{
				// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 2组装wheresql
				StringBuilder countSql = new StringBuilder(" select count(*) ");

				if (key != null) {
					countSql.append(" from ").append(" oa_document d join oa_attachment a on d.id=a.document_id");
					countSql.append(" where ").append(" d.name like ? or d.creatUser_id like ? ");
					countSql.append(" or a.name like ? or a.creatUser_id like ? ");
				}else {
					countSql.append(" from oa_attachment a ");
				}
				// 3查询总记录语句对象
				PreparedStatement ps= connection.prepareStatement(countSql.toString());
				// 3传入参数
				if(key != null) {
					int i=1;
					key= "%" + key + "%";
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
				}
				// 4执行语句对象并获取结果
				ResultSet rs=ps.executeQuery();
				int total=0;
				if (rs.next()) {
					total = rs.getInt(1);
				}
				// 没有满足查询查询条件的数据，直接返回
				if (total == 0) {
					return total;
				}
				// 执行分页查询
				StringBuilder querySql=new StringBuilder(" Select d.*,a.*");
				querySql.append(" from ").append(" oa_document d join oa_attachment a on d.id=a.document_id");
				querySql.append(" where ").append(" d.name like ? or d.creatUser_id like ? ");
				querySql.append(" or a.name like ? or a.creatUser_id like ? ");
				querySql.append(" order by name desc");
				querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);
				
				ps= connection.prepareStatement(querySql.toString());
				
				// 3传入参数
				if(key != null) {
					int i=1;
					key= "%" + key + "%";
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
					ps.setString(i++, key);
				}
				// 4执行语句对象并获取结果
				rs = ps.executeQuery();
				// 5转换结果为实体
				while (rs.next()) {
					RecycleEntity e = resultset2entity(rs);
					pageData.add(e);
				}
				return total;
	}

	@Override
	public void insert(RecycleEntity t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected RecycleEntity resultset2entity(ResultSet rs) throws Exception {
		RecycleEntity e= new RecycleEntity();
		e.setDocId(rs.getString("id"));
		e.setAtmId(rs.getString("id"));
		e.setDocName(rs.getString("name"));
		e.setAtmName(rs.getString("name"));
		e.setDocPath(rs.getString("path"));
		e.setAtmPath(rs.getString("path"));
		e.setDocCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		e.setAtmCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		String docCreateUserId = rs.getString("createUser_id");
		if (docCreateUserId != null) {
			SysUserEntity user =new SysUserEntity();
			user.setId(docCreateUserId);
			e.setDocCreateUserId(user);
		}
		String atmCreateUserId = rs.getString("createUser_id");
		if (atmCreateUserId != null) {
			SysUserEntity user =new SysUserEntity();
			user.setId(atmCreateUserId);
			e.setAtmCreateUserId(user);
		}
		String DocumentId= rs.getString("document_id");
		if(DocumentId != null) {
			DocumentEntity document= new DocumentEntity();
			document.setId(DocumentId);
			e.setDocumentId(document);
		}
		e.setDocDeptId(rs.getString("dept_id"));
		e.setAtmDeptId(rs.getString("dept_id"));
		
		return e;
	}

}
