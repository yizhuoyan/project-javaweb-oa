package com.neusoft.oa.organization.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.base.dao.SysUserDao;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;
import static com.neusoft.oa.core.util.AssertThrowUtil.*;

public class OrganizationFunctionImpl implements OrganizationFunction {

	@Override
	public EmployeeEntity addEmployee(EmployeeAo ao) throws Exception {
		//1验证参数
		String idcard=$("身份证号",ao.getIdcard());
		String departmentId=$("部门编号",ao.getDepartmentId());
		
		//2执行业务逻辑
		EmployeeEntity e=new EmployeeEntity();
		e.setId(DBUtil.uuid());
		e.setPoliticalStatus(ao.getPoliticalStatus());
		e.setIdcard(idcard);
		//e.setAge();
		e.setAddress(ao.getAddress());
		e.setEmail(ao.getEmail());
		e.setHomePhone(ao.getHomePhone());
		//e.setHiredate(ao.getHiredate());
		e.setWorkPhone(ao.getWorkPhone());
		DepartmentEntity dept=new DepartmentEntity();
		dept.setId(departmentId);
		e.setDepartment(dept);
		e.setNation(ao.getNation());
		e.setNativePlace(ao.getNativePlace());
		e.setDomicilePlace(ao.getDomicilePlace());
		//e.setMale(ao);
		//e.setBirthday();
		//e.setMarriageState();
		e.setName(ao.getName());
		e.setAccount(ao.getAccount());
		e.setPassword("123456");
		e.setCreateTime(new Date());
		//e.setPhone();
		//e.setSecurityEmail();
		//e.setAvatar();
		e.setRemark(ao.getRemark());
		//3组装业务结果
		EmployeeDao dao=DaoFactory.getDao(EmployeeDao.class);
		dao.insert(e);
		return e;
	}

	@Override
	public List<EmployeeEntity> addEmployee(List<EmployeeAo> ao) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeEntity modifyEmployee(String id, EmployeeAo ao) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetEmployeePassword(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PaginationQueryResult<EmployeeEntity> listEmployee(String key, int pageNo, int pageSize) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Integer.parseInt(System.getProperty("sys.default-page-size", "10"));
		}

		List<EmployeeEntity> pageData = new ArrayList<>(pageSize);
		// 2执行业务逻辑
		EmployeeDao udao = DaoFactory.getDao(EmployeeDao.class);
		int total = udao.selectsByKey(key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<EmployeeEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
	}

}
