package com.neusoft.oa.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.neusoft.oa.attendance.dao.DeptAtteCountDao;
import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DeptAtteCountDaoImpl extends TemplateDaoImpl<AtteCountEntity> implements DeptAtteCountDao {

	protected DeptAtteCountDaoImpl() {
		super("atte_count");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateAtteCount(String empId,int lie) throws Exception {
			// 1获取连接 AtteCountEntity
				Connection connection = DBUtil.getConnection();
				// 2创建sql语句对象
				StringBuilder sql = new StringBuilder();
				sql.append(" update ").append(" atte_count").append(" set ");
				sql.append(" tardycount").append(" =? ");
				sql.append(" where ").append( " emp_id").append(" =?");
				PreparedStatement ps = connection.prepareStatement(sql.toString());

				// 3传入参数并执行语句对象
				ps.setObject(1, lie);
				ps.setObject(2, empId);
				
				// 4获取执行结果
				ps.executeUpdate();
				
	}

	@Override
	public void insert(AtteCountEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AtteCountEntity> selectsAttetardy(List<AtteCountEntity> totalData) throws Exception {
		Connection connection = this.getConnection();

		StringBuilder sql = new StringBuilder();
		sql.append(" select emp_id,count(*) from ").append(" atte_attendances ");
		sql.append(
				" where  signin_time > (select sign_outstart from atte_day LIMIT 1 ) and signin_time < (select sign_inend from atte_day LIMIT 1 )")
		.append(" Group by emp_id");
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			

			ResultSet rs = ps.executeQuery();
			// 5转换结果为实体
			while (rs.next()) {
				
				AtteCountEntity u= new AtteCountEntity();
				String empId=rs.getString("emp_id");
				if(empId!=null) {
					EmployeeEntity e= new EmployeeEntity();
					e.setId(empId);
					u.setEmp(e);
				}
				u.setTardycount(rs.getInt("count(*)"));
				totalData.add(u);
	
			}
			System.out.println(totalData);
			return totalData;
			
		}
		

	}
	@Override
	public List<AtteCountEntity> selectsAtteretroactive(List<AtteCountEntity> totalData) throws Exception {
		Connection connection = this.getConnection();

		StringBuilder sql = new StringBuilder();
		sql.append("select emp_id,count(*) from ").append(" atte_retroactive ")		
		.append(" Group by emp_id");
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			

			ResultSet rs = ps.executeQuery();
			// 5转换结果为实体
			while (rs.next()) {				
				AtteCountEntity u= new AtteCountEntity();
				String empId=rs.getString("emp_id");
				if(empId!=null) {
					EmployeeEntity e= new EmployeeEntity();
					e.setId(empId);
					u.setEmp(e);
				}
				u.setRetroactivecount(rs.getInt("count(*)"));
				totalData.add(u);
	
			}
			System.out.println(totalData);
			return totalData;
		}
		
	}
	@Override
	public List<AtteCountEntity> selectsAttelate(List<AtteCountEntity> totalData) throws Exception {
		Connection connection = this.getConnection();

		StringBuilder sql = new StringBuilder();
		sql.append("select s.emp_id,count(*) from ").append(" atte_attendances s ")	
		.append(" where signin_time>(select on_duty from atte_day m join atte_attendances y on y.when_day=m.when_day  limit 1)" )
		.append(" and signin_time<(SELECT q.sign_inend from atte_day q JOIN atte_attendances w on q.when_day=w.when_day limit 1)" )
		.append(" Group by emp_id");
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			

			ResultSet rs = ps.executeQuery();
			// 5转换结果为实体
			while (rs.next()) {				
				AtteCountEntity u= new AtteCountEntity();
				String empId=rs.getString("emp_id");
				if(empId!=null) {
					EmployeeEntity e= new EmployeeEntity();
					e.setId(empId);
					u.setEmp(e);
				}
				u.setLatecount(rs.getInt("count(*)"));
				totalData.add(u);
	
			}
			System.out.println(totalData);
			return totalData;
		}
		
	}
	@Override
	public List<AtteCountEntity> selectsAtteEvection(List<AtteCountEntity> totalData) throws Exception {
		Connection connection = this.getConnection();

		StringBuilder sql = new StringBuilder();
		sql.append(" select emp_id,count(*) from ").append(" atte_vacate ")	
		.append(" where type='出差'	")
		.append(" Group by emp_id" );
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			

			ResultSet rs = ps.executeQuery();
			// 5转换结果为实体
			while (rs.next()) {				
				AtteCountEntity u= new AtteCountEntity();
				String empId=rs.getString("emp_id");
				if(empId!=null) {
					EmployeeEntity e= new EmployeeEntity();
					e.setId(empId);
					u.setEmp(e);
				}
				u.setEvectioncount(rs.getInt("count(*)"));
				totalData.add(u);
	
			}
			System.out.println(totalData);
			return totalData;
		}
		
		
	}
	@Override
	public List<AtteCountEntity> selectsAtteVacate(List<AtteCountEntity> totalData) throws Exception {
		Connection connection = this.getConnection();

		StringBuilder sql = new StringBuilder();
		sql.append(" select emp_id,count(*) from ").append(" atte_vacate ")	
		.append(" where type<>'出差'	")
		.append(" Group by emp_id" );
		try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
			

			ResultSet rs = ps.executeQuery();
			// 5转换结果为实体
			while (rs.next()) {				
				AtteCountEntity u= new AtteCountEntity();
				String empId=rs.getString("emp_id");
				if(empId!=null) {
					EmployeeEntity e= new EmployeeEntity();
					e.setId(empId);
					u.setEmp(e);
				}
				u.setVacatecount(rs.getInt("count(*)"));
				totalData.add(u);
	
			}
			System.out.println(totalData);
			return totalData;
		}				
	}

	@Override
	protected AtteCountEntity resultset2entity(ResultSet rs) throws Exception {
	
		return null;
	}
	
}