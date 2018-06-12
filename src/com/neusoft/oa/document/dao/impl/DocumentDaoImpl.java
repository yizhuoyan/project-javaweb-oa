package com.neusoft.oa.document.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.document.dao.DocumentDao;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DocumentDaoImpl extends TemplateDaoImpl<DocumentEntity> implements DocumentDao {

	protected DocumentDaoImpl() {
		super("oa_document");
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void insert(DocumentEntity t) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建sql语句对象
		String sql = DBUtil.generateInsertSql(this.tableName,
				"id,property,path,name,createUser_id,createTime,remark,flag,dept_id");
		PreparedStatement ps = connection.prepareStatement(sql);
		// 3传入参数并执行语句对象
		int i = 1;
		ps.setString(i++, t.getId());
		ps.setString(i++, t.getProperty());
		ps.setString(i++, t.getPath());
		ps.setString(i++, t.getName());
		ps.setString(i++, t.getCreateUserId().getId());
		ps.setObject(i++, this.instant2timestamp(t.getCreateTime()));
		ps.setString(i++, t.getRemark());
		ps.setObject(i++, t.getFlag());
		ps.setString(i++, t.getDeptId().getId());

		// 4获取执行结果（如果是查询，则转化为实体）
		ps.executeUpdate();

	}

	@Override
	protected DocumentEntity resultset2entity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		// id,property,path,name,createUser_id,createTime,remark,flag,dept_id
		DocumentEntity d = new DocumentEntity();
		d.setCreateTime(timestamp2Instant(rs.getTimestamp("createTime")));
		
		String creatUserId = rs.getString("createUser_id");
		EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
		d.setCreateUserId(edao.select("id", creatUserId));
		
		String deptId = rs.getString("dept_id");
		DepartmentDao ddao = DaoFactory.getDao(DepartmentDao.class);
		d.setDeptId(ddao.select("id", deptId));
		
		d.setFlag(rs.getInt("flag"));
		d.setId(rs.getString("id"));
		d.setName(rs.getString("name"));
		d.setPath(rs.getString("path"));
		d.setProperty(rs.getString("property"));
		d.setRemark(rs.getString("remark"));

		return d;
	}

	@Override
	public List<DocumentEntity> selectsByKey(String key) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 2创建语句对象
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(this.tableName).append(" where flag!=0 ");
		if (key != null) {
			sql.append(" and ( property like ? or name like ? or createUser_id like ? or remark like ? )");

		}

		sql.append(" order by createTime");

		PreparedStatement ps = connection.prepareStatement(sql.toString());
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
		// 5转换结果为实体
		List<DocumentEntity> result = new LinkedList<>();
		while (rs.next()) {
			DocumentEntity e = resultset2entity(rs);
			result.add(e);
		}
		return result;
	}

	@Override
	public int selectsByKey(String deptId,String key, int pageNo, int pageSize, List<DocumentEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder countSql = new StringBuilder("select count(*)");

		countSql.append(" from ").append(this.tableName).append(" where flag!=0 and dept_id=? ");
		if (key != null) {
			countSql.append(" and ( property like ? or name like ? or createUser_id like ? or remark like ? )");

		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement(countSql.toString());
		// 3传入参数
		ps.setString(1, deptId);
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
		querySql.append(" select *");
		querySql.append(" from ").append(this.tableName);
		querySql.append(" where flag!=0 and dept_id=? ");
		if (key != null) {
			querySql.append(" and property like ? or name like ? or createUser_id like ? or remark like ? ");
		}
		querySql.append(" order by name desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		ps.setString(1, deptId);
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

}
