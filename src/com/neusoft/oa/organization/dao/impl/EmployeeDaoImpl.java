package com.neusoft.oa.organization.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.neusoft.oa.base.dao.SysUserDao;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.entity.MarriageState;

public class EmployeeDaoImpl extends TemplateDaoImpl<EmployeeEntity> implements EmployeeDao {

	public EmployeeDaoImpl() {
		super("oa_emp","uid,birthday,address,nationality," 
				+ "politicalStatus,homePhone,"
				+ "marriageState,hiredate,idcard,"
				+ "nativePlace,workPhone,domicilePlace," 
				+ "male,department_id,age,workemail");
	}

	@Override
	public void insert(EmployeeEntity u) throws Exception {

		// 1 获取数据库连接
		Connection connection = DBUtil.getConnection();
		SysUserDao userDao = DaoFactory.getDao(SysUserDao.class);
		userDao.insert(u);
		// 2 创建语句对象
		try (PreparedStatement ps = connection.prepareStatement(generateInsertSql())) {
			// 3 执行语句对象（设置参数）
			int i = 1;
			ps.setString(i++, u.getId());
			ps.setDate(i++, localDate2sqlDate(u.getBirthday()));
			ps.setString(i++, u.getAddress());
			ps.setInt(i++, u.getNationality());
			ps.setInt(i++, u.getPoliticalStatus());
			ps.setString(i++, u.getHomePhone());
			ps.setInt(i++, u.getMarriageState());
			ps.setDate(i++, localDate2sqlDate(u.getHiredate()));
			ps.setString(i++, u.getIdcard());
			ps.setString(i++, u.getNativePlace());
			ps.setString(i++, u.getWorkPhone());
			ps.setString(i++, u.getDomicilePlace());
			ps.setBoolean(i++, u.isMale());
			ps.setString(i++, u.getDepartment().getId());
			ps.setInt(i++, u.getAge());
			ps.setString(i++, u.getWorkEmail());
			ps.executeUpdate();
		}
	}

	@Override
	public void delete(String ufield, Object value) throws Exception {
		// 修改状态即可
		EmployeeEntity e = this.select(ufield, value);
		SysUserDao udao = DaoFactory.getDao(SysUserDao.class);
		udao.update(e.getId(), "flag", EmployeeEntity.FLAG_DELETED);

	}

	@Override
	public boolean exist(String ufield, Object value) throws Exception {
		Connection connection = this.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)").append(" from sys_user u inner join oa_emp e on e.uid=u.id")
				.append(" where u.flag!=").append(EmployeeEntity.FLAG_DELETED).append(" and ").append(ufield)
				.append("=?");

		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setObject(1, value);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1) > 0;
		}
		return false;
	}

	@Override
	public EmployeeEntity select(String ufield, Object value) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select *").append(" from sys_user u inner join oa_emp e on e.uid=u.id").append(" where u.flag!=")
				.append(EmployeeEntity.FLAG_DELETED).append(" and ").append(ufield).append("=? limit 0,1");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setObject(1, value);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return resultset2entity(rs);
		}
		return null;
	}

	@Override
	public List<EmployeeEntity> selectAllByColumn(String field, Object value, String... orderbys) throws Exception {

		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select *").append(" from sys_user u inner join oa_emp e on e.uid=u.id").append(" where u.flag!=")
				.append(EmployeeEntity.FLAG_DELETED).append(" and ").append(field);
		if (value == null) {
			sql.append(" is null ");
		} else {
			sql.append("=? ");
		}
		if (orderbys.length > 0) {
			sql.append(" order by ");
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length() - 1, ' ');
		}
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		if (value != null) {
			ps.setObject(1, value);
		}
		ResultSet rs = ps.executeQuery();
		List<EmployeeEntity> result = new LinkedList<>();
		while (rs.next()) {
			result.add(resultset2entity(rs));
		}
		return result;
	}

	@Override
	public List<EmployeeEntity> selectAll(String... orderbys) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select *").append(" from sys_user u inner join oa_emp e on e.uid=u.id").append(" where u.flag!=")
				.append(EmployeeEntity.FLAG_DELETED);
		if (orderbys.length > 0) {
			sql.append(" order by ");
			for (String orderby : orderbys) {
				sql.append(orderby).append(",");
			}
			sql.setCharAt(sql.length() - 1, ' ');
		}
		PreparedStatement ps = connection.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		List<EmployeeEntity> result = new LinkedList<>();
		while (rs.next()) {
			result.add(resultset2entity(rs));
		}
		return result;
	}

	@Override
	public void update(Serializable id, Map<String, Object> columnsMap) throws Exception {
		Map<String,Object> userTable=new HashMap<>();
		Map<String,Object> empTable=new HashMap<>();
		for(String column:columnsMap.keySet()) {
			if(this.containsColumn(column)) {
				empTable.put(column, columnsMap.get(column));
			}else {
				userTable.put(column, columnsMap.get(column));
			}
		}
		if(userTable.size()>0) {
			SysUserDao udao=DaoFactory.getDao(SysUserDao.class);
			udao.update(id, userTable);
		}
		
		if(empTable.size()>0) {
			super.update(id, empTable);
		}
		
	}

	@Override
	public void update(Serializable id, String column, Object value) throws Exception {
		// 判断是哪张表的列
		if (this.containsColumn(column)) {
			// 员工表
			super.update(id, column, value);
		}else {
			// 1如果是用户表
			SysUserDao udao = DaoFactory.getDao(SysUserDao.class);
			udao.update(id, column, value);
			return;
		}
	}

	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<EmployeeEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder countSql = new StringBuilder("select count(*)");

		if (key != null) {
			countSql.append(" from ").append("oa_emp e join sys_user  u on e.uid=u.id ");
			countSql.append(" where u.account like ? or u.name like ? or e.idcard like ? ");
		} else {
			countSql.append(" from oa_emp e ");
		}

		// 2查询总记录语句对象
		PreparedStatement ps = connection.prepareStatement(countSql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
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
		querySql.append(" select e.*,u.*");
		querySql.append(" from ").append("oa_emp e join sys_user  u on e.uid=u.id ");
		querySql.append(" where u.flag!=").append(EmployeeEntity.FLAG_DELETED);
		if (key != null) {
			querySql.append(" and u.account like ? or u.name like ? or e.idcard like ? ");
		}
		querySql.append(" order by account desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		if (key != null) {
			int i = 1;
			key = "%" + key + "%";
			ps.setString(i++, key);
			ps.setString(i++, key);
			ps.setString(i++, key);
		}
		// 4执行语句对象并获取结果
		rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) {
			EmployeeEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;
	}

	@Override
	protected EmployeeEntity resultset2entity(ResultSet rs) throws Exception {
		EmployeeEntity e = new EmployeeEntity();
		e.setId(rs.getString("id"));
		e.setIdcard(rs.getString("idcard"));
		e.setMarriageState(rs.getInt("MarriageState"));
		e.setAge(rs.getInt("age"));
		e.setMale(rs.getBoolean("male"));
		e.setAddress(rs.getString("address"));
		e.setWorkEmail(rs.getString("workemail"));
		e.setHomePhone(rs.getString("homePhone"));
		e.setHiredate(sqlDate2LocalDate(rs.getDate("hiredate")));
		e.setWorkPhone(rs.getString("workPhone"));
		String departmentId = rs.getString("department_id");
		if (departmentId != null) {
			DepartmentEntity department = new DepartmentEntity();
			department.setId(departmentId);
			e.setDepartment(department);
		}
		e.setNationality(rs.getInt("nationality"));
		e.setNativePlace(rs.getString("nativePlace"));
		e.setDomicilePlace(rs.getString("domicilePlace"));
		e.setBirthday(sqlDate2LocalDate(rs.getDate("birthday")));
		e.setPoliticalStatus(rs.getInt("politicalStatus"));
		e.setName(rs.getString("name"));
		e.setAccount(rs.getString("account"));
		e.setLastLoginIP(rs.getString("lastLoginIP"));
		e.setPassword(rs.getString("password"));
		e.setCreateTime(rs.getTimestamp("createTime"));
		e.setLastLoginTime(rs.getTimestamp("lastLoginTime"));
		e.setPhone(rs.getString("phone"));
		e.setSecurityEmail(rs.getString("securityEmail"));
		e.setAvatar(rs.getString("avatar"));
		e.setRemark(rs.getString("remark"));
		e.setFlag(rs.getInt("flag"));
		e.setLastModPasswordTime(rs.getTimestamp("lastModPasswordTime"));
		return e;
	}
}
