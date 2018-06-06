package com.neusoft.oa.organization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neusoft.oa.base.dao.SysUserDao;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.entity.MarriageState;

public class EmployeeDaoImpl extends TemplateDaoImpl<EmployeeEntity> implements EmployeeDao {

	public EmployeeDaoImpl() {
		super("oa_emp");
	}

	@Override
	public int selectsByKey(String key, int pageNo, int pageSize, List<EmployeeEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 组装wheresql
		StringBuilder countSql = new StringBuilder("select count(*)");

		if (key != null) {
			countSql.append(" from ").append("oa_emp e join sys_user  u on e.id=u.id ");
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
		querySql.append(" from ").append("oa_emp e join sys_user  u on e.id=u.id ");
		if (key != null) {
			querySql.append(" where u.account like ? or u.name like ? or e.idcard like ? ");
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
	public void insert(EmployeeEntity u) throws Exception {
		
		
		// 1 获取数据库连接
		Connection connection = DBUtil.getConnection();
		SysUserDao userDao=DaoFactory.getDao(SysUserDao.class);
		userDao.insert(u);
		// 2 创建语句对象
		String sql = DBUtil.generateInsertSql(tableName,
				"id,birthday,address,nation,politicalStatus,"
				+ "homePhone,marriageState,hiredate,"
				+ "idcard,nativePlace,workPhone,domicilePlace,"
				+ "male,department_id,age,email");
		try (PreparedStatement ps = connection.prepareStatement(sql.toString());) {
			// 3 执行语句对象（设置参数）
			int i = 1;
			ps.setString(i++, u.getId());
			ps.setDate(i++, localDate2sqlDate(u.getBirthday()));
			ps.setString(i++, u.getAddress());
			ps.setString(i++, u.getNation());
			ps.setString(i++, u.getPoliticalStatus());
			ps.setString(i++, u.getHomePhone());
			MarriageState m=u.getMarriageState();
			if(m==null) {
				ps.setObject(i++,null);
			}else {
				ps.setInt(i++, m.id);
			}
			ps.setTimestamp(i++, instant2timestamp(u.getHiredate()));
			ps.setString(i++, u.getIdcard());
			ps.setString(i++, u.getNativePlace());
			ps.setString(i++, u.getWorkPhone());
			ps.setString(i++, u.getDomicilePlace());
			ps.setBoolean(i++, u.isMale());
			ps.setString(i++, u.getDepartment().getId());
			ps.setInt(i++, u.getAge());
			ps.setString(i++, u.getEmail());
			ps.executeUpdate();
		}
	}

	@Override
	protected EmployeeEntity resultset2entity(ResultSet rs) throws Exception {
		EmployeeEntity e = new EmployeeEntity();
		e.setId(rs.getString("id"));
		e.setIdcard(rs.getString("idcard"));
		e.setMarriageState(MarriageState.of(rs.getInt("MarriageState")));
		e.setAge(rs.getInt("age"));
		e.setMale(rs.getBoolean("male"));
		e.setAddress(rs.getString("address"));
		e.setEmail(rs.getString("email"));
		e.setHomePhone(rs.getString("homePhone"));
		e.setHiredate(timestamp2Instant(rs.getTimestamp("hiredate")));
		e.setWorkPhone(rs.getString("workPhone"));
		String departmentId = rs.getString("department_id");
		if (departmentId != null) {
			DepartmentEntity department = new DepartmentEntity();
			department.setId(departmentId);
			e.setDepartment(department);
		}
		e.setNation(rs.getString("nation"));
		e.setNativePlace(rs.getString("nativePlace"));
		e.setDomicilePlace(rs.getString("domicilePlace"));
		e.setBirthday(sqlDate2LocalDate(rs.getDate("birthday")));
		e.setPoliticalStatus(rs.getString("politicalStatus"));
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
