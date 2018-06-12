package com.neusoft.oa.attendance.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.attendance.dao.AttendanceDao;
import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

/**
 * @author 田梦源
 *
 */

public class AttendanceDaoImpl extends TemplateDaoImpl<AttendanceEntity> implements AttendanceDao {

	protected AttendanceDaoImpl() {
		super("atte_attendances");
	
	}
	
	@Override
	public void insert(AttendanceEntity t) throws Exception {
		String sql=DBUtil.generateInsertSql(this.tableName, "id,emp_id,when_day,signin_time,remark,ampm,isvalid");	
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int i=1;
		ps.setString(i++, t.getId());
		ps.setString(i++, t.getEmp().getId());
		java.util.Date es=t.getWhenDay();	
		if(es!=null) {
			java.sql.Date sqlDate = new java.sql.Date(es.getTime());
			ps.setDate(i++,sqlDate);
		}else {
			ps.setDate(i++,null);
		}
		LocalTime signinTime=t.getSigninTime();
		if(signinTime!=null) {
			Time time=Time.valueOf(signinTime);
			ps.setTime(i++,time);
		}else {
			ps.setTime(i++, null);
		}	
		ps.setString(i++, t.getRemark());
		ps.setInt(i++,t.getAmpm() );
		ps.setInt(i++, t.getIsvalid());
		ps.executeUpdate();
	}
	
	@Override
	public List<AttendanceEntity> selectsByEmployee(String empid) throws Exception {
		String sql="SELECT * FROM atte_attendances  where emp_id=? and when_Day=? ORDER BY  signin_time DESC";
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, empid);
		ps.setDate(2, new Date(System.currentTimeMillis()));
		ResultSet rs = ps.executeQuery();
		List<AttendanceEntity> result=new LinkedList<>();
		while(rs.next()) {
			AttendanceEntity e=resultset2entity(rs);
			result.add(e);
		}
		return result;
	}
	
	@Override
	protected AttendanceEntity resultset2entity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		AttendanceEntity e=new AttendanceEntity();
		EmployeeEntity emp=new EmployeeEntity();
		String empId=rs.getString("emp_id");
		emp.setId(empId);
		e.setId(rs.getString("id"));
		e.setEmp(emp);
		e.setRemark(rs.getString("remark"));
		e.setWhenDay(rs.getDate("when_day"));
		Time time=rs.getTime("signin_time");
		if(time!=null) {
			e.setSigninTime(time.toLocalTime());
		}
		e.setAmpm(rs.getInt("ampm"));
		e.setIsvalid(rs.getInt("isvalid"));
		return e;
	}

	@Override
	public AttendanceEntity selectsSignColumn(String empid, int isvalid) throws Exception {
		String sql="SELECT * FROM atte_attendances  where emp_id=? and when_day= ? and isvalid= ?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int i=1;
		ps.setString(i++, empid);
		ps.setDate(i++, new Date(System.currentTimeMillis()));
		ps.setInt(i++, isvalid);
	
		ResultSet rs = ps.executeQuery();
		AttendanceEntity result=new AttendanceEntity();
		if(rs.next()) {
			result=resultset2entity(rs);
		}
		return result;
	}

	@Override
	public void updateIsvalid(String empid, int isvalid) throws Exception {
		String sql="UPDATE atte_attendances SET isvalid = 0 WHERE isvalid = ? and  emp_id=? and when_day=? ";
		Connection connection=DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int i=1;
		ps.setInt(i++, isvalid);
		ps.setString(i++, empid);
		ps.setDate(i++, new Date(System.currentTimeMillis()));
			
		ps.executeUpdate();
	}

}
