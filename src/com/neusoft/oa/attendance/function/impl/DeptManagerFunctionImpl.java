package com.neusoft.oa.attendance.function.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.neusoft.oa.attendance.dao.DeptAtteCountDao;
import com.neusoft.oa.attendance.dao.DeptAttendanceDao;
import com.neusoft.oa.attendance.dao.ManagerAtteCountDao;
import com.neusoft.oa.attendance.dao.ManagerAtteRetroactiveDao;
import com.neusoft.oa.attendance.dao.ManagerAtteVacateDao;
import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.attendance.entity.AtteRetroactiveEntity;
import com.neusoft.oa.attendance.entity.AtteVacateEntity;
import com.neusoft.oa.attendance.entity.AttendanceEntity;
import com.neusoft.oa.attendance.function.DeptManagerFunction;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.dto.QueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class DeptManagerFunctionImpl implements DeptManagerFunction {

	@Override
	public PaginationQueryResult<AtteCountEntity> queryCounts(String managerid, String key, int pageNo,
			
			
			int pageSize) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
		}

		this.addAtteCounts();
		List<AtteCountEntity> pageData = new ArrayList<>(pageSize);
		// 2执行业务逻辑
		ManagerAtteCountDao dao=DaoFactory.getDao(ManagerAtteCountDao.class);
		String departmentId=dao.selectsDepartmentId(managerid);
				
		int total = dao.selectsAtteCount(departmentId, key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<AtteCountEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
	}

	@Override
	public PaginationQueryResult<AtteVacateEntity> queryVacate(String managerid, String key, int pageNo, int pageSize)
			throws Exception {
		// 1清理验证参数
				key = ThisSystemUtil.trim(key);
				if (pageNo <= 0) {
					pageNo = 1;
				}
				if (pageSize <= 0) {
					pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
				}

				List<AtteVacateEntity> pageData = new ArrayList<>(pageSize);
				// 2执行业务逻辑
				//ManagerAtteCountDao dao=DaoFactory.getDao(ManagerAtteCountDao.class);
				//String departmentId=dao.selectsDepartmentId(managerid);
				//System.out.println("部门id"+departmentId);				
				//int total = dao.selectsAtteCount(departmentId, key, pageNo, pageSize, pageData);
				ManagerAtteVacateDao dao=DaoFactory.getDao(ManagerAtteVacateDao.class);
				String departmentId=dao.selectsDepartmentId(managerid);
				int total = dao.selectsAtteVacate(departmentId, key, pageNo, pageSize, pageData);
				// 3组装业务结果
				PaginationQueryResult<AtteVacateEntity> result = new PaginationQueryResult<>();
				result.setTotalRows(total);
				result.setRows(pageData);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				return result;

	}

	@Override
	public PaginationQueryResult<AtteRetroactiveEntity> queryRetroactive(String managerid, String key, int pageNo,
			int pageSize) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
		}

		List<AtteRetroactiveEntity> pageData = new ArrayList<>(pageSize);
		
		ManagerAtteRetroactiveDao dao=DaoFactory.getDao(ManagerAtteRetroactiveDao.class);
		String departmentId=dao.selectsDepartmentId(managerid);
		int total = dao.selectsAtteRetroactive(departmentId, key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<AtteRetroactiveEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
		
	}

	@Override
	public PaginationQueryResult<AttendanceEntity> queryAttendance(String managerid, String key, int pageNo,
			int pageSize) throws Exception {
		// 1清理验证参数
				key = ThisSystemUtil.trim(key);
				if (pageNo <= 0) {
					pageNo = 1;
				}
				if (pageSize <= 0) {
					pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
				}

				List<AttendanceEntity> pageData = new ArrayList<>(pageSize);
				// 2执行业务逻辑
				DeptAttendanceDao dao=DaoFactory.getDao(DeptAttendanceDao.class);
				String departmentId=dao.selectsDepartmentId(managerid);
				
				System.out.println("部门id"+departmentId);
				
				int total = dao.selectsAttedance(departmentId, key, pageNo, pageSize, pageData);
				// 3组装业务结果
				PaginationQueryResult<AttendanceEntity> result = new PaginationQueryResult<>();
				result.setTotalRows(total);
				result.setRows(pageData);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				return result;
	}

	@Override
	public void addAtteCounts() throws Exception {
		
		List<AtteCountEntity> totalData= new LinkedList<>();
		
		DeptAtteCountDao dao=DaoFactory.getDao(DeptAtteCountDao.class);
		List<AtteCountEntity> list=  dao.selectsAttetardy(totalData);
//		if(list.isEmpty()) {
//			OAException.throwWithMessage("迟到记录不存在",list);
//		}
		int tardycount=0;
		String empId="";
		for (AtteCountEntity integ:list) {
			tardycount=integ.getTardycount();
			empId=integ.getEmp().getId();
			if(empId!=null) {	
				dao.updateAtteCount(empId,tardycount);
			}
		}
		 list=  dao.selectsAttelate(totalData);
		int latecount=0;
		for (AtteCountEntity integ:list) {
			latecount=integ.getLatecount();
			empId=integ.getEmp().getId();
			if(empId!=null) {	
				dao.updateAtteCount(empId,latecount);
			}
		}
		 list=  dao.selectsAtteretroactive(totalData);
		int retroactivecount=0;
		for (AtteCountEntity integ:list) {
			retroactivecount=integ.getRetroactivecount();
			empId=integ.getEmp().getId();
			if(empId!=null) {	
				dao.updateAtteCount(empId,retroactivecount);
			}
		}
		 list=  dao.selectsAtteEvection(totalData);
		int evectioncount=0;
		for (AtteCountEntity integ:list) {
			evectioncount=integ.getEvectioncount();
			empId=integ.getEmp().getId();
			if(empId!=null) {	
				dao.updateAtteCount(empId,evectioncount);
			}
		}
		
		list=  dao.selectsAtteVacate(totalData);
		int vacatecount=0;
		for (AtteCountEntity integ:list) {
			vacatecount=integ.getVacatecount();
			empId=integ.getEmp().getId();
			if(empId!=null) {	
				dao.updateAtteCount(empId,vacatecount);
			}
		}		
		QueryResult<AtteCountEntity> result=new QueryResult<>();
		
		System.out.println(list);
		System.out.println(result);
	
	}

	
	



}
