package com.neusoft.oa.attendance.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import  java.sql.Connection;
import com.neusoft.oa.attendance.dao.ManagerAtteCountDao;
import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.TemplateDaoImpl;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class ManagerAtteCountDaoImpl extends TemplateDaoImpl<AtteCountEntity> implements ManagerAtteCountDao {

	

	protected ManagerAtteCountDaoImpl() {
		super("atte_count");
		// TODO Auto-generated constructor stub
	}




	@Override
	public int selectsAtteCount(String departmentid, String key,int pageNo,int pageSize,List<AtteCountEntity> pageData)throws Exception{
		//select m.*,s.department_id from atte_count m join oa_emp s on m.emp_id=s.id 
		//where s.department_id=(SELECT department_id FROM oa_emp WHERE id="293a568ed2c8034917540e0a687d1e3f")
				// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 3创建预编译语句对象PreparedStatement
				StringBuilder whereSql = new StringBuilder();
				whereSql.append(" from atte_count m ")
				.append(" join oa_emp s ")
				.append(" on m.emp_id=s.id ")
				.append(" join sys_user p ")
				.append(" on m.emp_id=p.id ");
				//if(key!=null) {
				whereSql.append(" where s.department_id = ?");
				
				//.append(" (SELECT t.department_id FROM oa_emp t WHERE t.id= ?)");
				
				//.append(" or m.mouth like ? or ")
				//.append(" m.tardycount like ? or m.latecount like  ?or m.vacatecount like  ? ");
				//}
				PreparedStatement ps = connection.prepareStatement("select count(*) "+whereSql);
				
				ps.setString(1, departmentid);
				
				
				//if(key!=null) {
				//	key="%" + key + "%";
				//	ps.setString(1, key);
				//	ps.setString(2, key);
				//	ps.setString(3, key);
				//	ps.setString(4, key);
				//	ps.setString(5, key);
				//}else {
				//System.out.println("key=null");
				//}
				// 4传参并执行语句executeQuery
				ResultSet rs = ps.executeQuery();
				//分页查询获取总行数
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
				querySql.append(" from ").append(" oa_emp e join atte_count  u on e.id=u.emp_id ")
				.append(" join sys_user p ")
				.append(" on u.emp_id=p.id ")
				.append("where department_id = ? ");
				if (key != null) {					
					
					querySql.append(" and ( u.mouth like ? or u.tardycount like ? or u.latecount like  ? or u.vacatecount like  ?) ");
				}
				querySql.append(" order by e.id desc");
				querySql.append(" limit ").append((pageNo - 1) * pageSize).append(",").append(pageSize);

				ps = connection.prepareStatement(querySql.toString());
				// 3传入参数
				ps.setString(1, departmentid);
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
					AtteCountEntity e = resultset2entity(rs);
					pageData.add(e);
				}
				return total;
			}
			
			
		
		

	@Override
	public void insert(AtteCountEntity t) throws Exception {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected AtteCountEntity resultset2entity(ResultSet rs) throws Exception {
		AtteCountEntity u = new AtteCountEntity();
		EmployeeEntity e=new EmployeeEntity();
		e.setName(rs.getString("name"));
		u.setEmp(e);
		u.setAbsenteeismcount(rs.getInt("absenteeismcount"));
		u.setEvectioncount(rs.getInt("evectioncount"));
		u.setMouth(rs.getInt("mouth"));
		u.setLatecount(rs.getInt("latecount"));
		u.setTardycount( rs.getInt("tardycount"));
		u.setRetroactivecount(rs.getInt("retroactivecount"));
		u.setVacatecount(rs.getInt("vacatecount"));		
		return u;
		
	}




	@Override
	public String selectsDepartmentId(String managerid) throws Exception {
		// 1获取连接
		Connection connection = DBUtil.getConnection();
		// 3创建预编译语句对象PreparedStatement
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" from oa_emp  ")		
		.append(" where id= ? ");
		PreparedStatement ps = connection.prepareStatement("select department_id "+whereSql);
		ps.setString(1, managerid);
		// 4执行语句对象并获取结果
		ResultSet rs = ps.executeQuery();
		// 5转换结果为实体
		while(rs.next()){      //这里必须循环遍历
			String result = rs.getString("department_id");//返回一条记录			
			return result;
			}
		
		return null;
		
		
	}




	@Override
	public  String selectEmpName() throws Exception {
				// 1获取连接
				Connection connection = DBUtil.getConnection();
				// 3创建预编译语句对象PreparedStatement
				StringBuilder whereSql = new StringBuilder();
				whereSql.append(" from sys_user s  ")
				.append(" join atte_count m")
				.append(" on s.id=m.emp_id ");
				
				PreparedStatement ps = connection.prepareStatement("select s.name "+whereSql);
				
				// 4执行语句对象并获取结果
				ResultSet rs = ps.executeQuery();
				// 5转换结果为实体
				while(rs.next()){      //这里必须循环遍历
					String result = rs.getString("name");//返回一条记录

					return result;
					}				
				return null;	
	}
	



	

	
	
}
