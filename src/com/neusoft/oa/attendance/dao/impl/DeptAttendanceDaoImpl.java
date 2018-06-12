package com.neusoft.oa.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.neusoft.oa.attendance.dao.DeptAttendanceDao;
import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DeptAttendanceDaoImpl extends TemplateDaoImpl<AttendanceEntity> implements DeptAttendanceDao {

	protected DeptAttendanceDaoImpl() {
		super("atte_attendances");
	}

	@Override
	public int selectsAttedance(String departmentId, String key, int pageNo, int pageSize,
			List<AttendanceEntity> pageData) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 3创建预编译语句对象PreparedStatement
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from atte_attendances m ").append(" join oa_emp s ").append(" on m.emp_id=s.id ")
				.append(" join sys_user p ").append(" on m.emp_id=p.id ");

		whereSql.append(" where s.department_id = ?");

		PreparedStatement ps = connection.prepareStatement("select count(*) " + whereSql);

		ps.setString(1, departmentId);

		ResultSet rs = ps.executeQuery();
		// 分页查询获取总行数
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
		querySql.append(" select p.name,e.department_id,u.*");
		querySql.append(" from ").append(" oa_emp e join atte_attendances  u on e.id=u.emp_id ")
				.append(" join sys_user p ").append(" on u.emp_id=p.id ").append("where department_id = ? ");
		if (key != null) {

			querySql.append(" and ( u.when_day like ? or u.signin_time like ? or u.remark like  ?) ");
		}
		querySql.append(" order by e.id desc");
		querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

		ps = connection.prepareStatement(querySql.toString());
		// 3传入参数
		ps.setString(1, departmentId);
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
			AttendanceEntity e = resultset2entity(rs);
			pageData.add(e);
		}
		return total;

	}

	@Override
	protected AttendanceEntity resultset2entity(ResultSet rs) throws Exception {
		AttendanceEntity u = new AttendanceEntity();
		EmployeeEntity e = new EmployeeEntity();
		e.setName(rs.getString("name"));
		u.setEmp(e);
		u.setId(rs.getString("id"));
		u.setSigninTime(sqlTime2LocalTime(rs.getTime("signin_time")));
		u.setWhenDay(rs.getDate("when_day"));
		return u;

	}

	@Override
	public String selectsDepartmentId(String managerid) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 3创建预编译语句对象PreparedStatement
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from oa_emp  ").append(" where id= ? ");
		PreparedStatement ps = connection.prepareStatement("select department_id " + whereSql);
		ps.setString(1, managerid);
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) { // 这里必须循环遍历
			String result = rs.getString("department_id");// 返回一条记录
			return result;
		}

		return null;

	}

	@Override
	public String selectEmpName() throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 3创建预编译语句对象PreparedStatement
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from sys_user s  ").append(" join atte_attendances m").append(" on s.id=m.emp_id ");

		PreparedStatement ps = connection.prepareStatement("select s.name " + whereSql);

		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		while (rs.next()) { // 这里必须循环遍历
			String result = rs.getString("name");// 返回一条记录

			return result;
		}
		return null;
	}

	@Override
	public void insert(AttendanceEntity t) throws Exception {

	}

}
