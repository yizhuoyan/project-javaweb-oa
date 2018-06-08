package com.neusoft.oa.organization.function.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.oa.base.dao.SysModuleDao;
import com.neusoft.oa.base.dao.SysUserDao;
import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.entity.SysUserEntity;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.ao.DepartmentAo;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;
import static com.neusoft.oa.core.util.AssertThrowUtil.*;

public class OrganizationFunctionImpl implements OrganizationFunction {

	@Override
	public List<DepartmentEntity> loadCanBeParentList(String id) throws Exception {
		id=ThisSystemUtil.trim(id);
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		if(id!=null) {
			//找到对应code
			DepartmentEntity excludeDept=dao.select("id",id);
			assertNotNull("id{1}无效",excludeDept,id);
			String treeCode=excludeDept.getCode();
			List<DepartmentEntity> result= dao.selectsExcludeWithDescendantByTreeCode(treeCode);
			return result;
		}else {
			return dao.selectAll("code");
		}
	}
	
	@Override
	public DepartmentEntity loadDepartment(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		DepartmentEntity e = dao.select("id", id);
		// 2.1 验证id
		if (e == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		// 2.2 查询出父模块
		DepartmentEntity parent=e.getParent();
		if (parent!= null) {
			parent= dao.select("id", parent.getId());
			e.setParent(parent);
		}
		//2.3 查询部门经理
		EmployeeEntity manager=e.getManager();
		if(manager!=null) {
			EmployeeDao edao=DaoFactory.getDao(EmployeeDao.class);
			manager=edao.select("id", manager.getId());
			e.setManager(manager);
		}
		//3组装业务结果
		return e;	
	}
	@Override
	public DepartmentEntity addDepartment(DepartmentAo ao) throws Exception {
				// 1 清理验证参数
				String parentId = ThisSystemUtil.trim(ao.getParentId());
				String managerId = ThisSystemUtil.trim(ao.getManagerId());
				String code = $("代号", ao.getCode());
				String name = $("名字", ao.getName());
				String remark = ThisSystemUtil.trim(ao.getRemark());
				// 2 执行业务逻辑
				DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
				// 2.1验证父模块是否存在
				DepartmentEntity parent=null;
				if (parentId != null) {
					parent=dao.select("id", parentId);
					if (parent==null) {
						OAException.throwWithMessage("父部门【?】不存在", parentId);
					}
				}
				// 2.2代号
				String parentCode=parent==null?"":parent.getCode();
				//长度
				if(code.length()-2!=parentCode.length()) {
					OAException.throwWithMessage("代号非法", parentId);
				}
				//前面部分必须和父代号一致
				if(!code.startsWith(parentCode)) {
					OAException.throwWithMessage("代号非法", parentId);
				}
				//不能重复
				if (dao.exist("code", code)) {
					OAException.throwWithMessage("代号【?】已存在", code);
				}
				
				//2.3 部门经理
				EmployeeEntity manager=null;
				if(managerId!=null) {
					EmployeeDao edao=DaoFactory.getDao(EmployeeDao.class);
					if(!edao.exist("id",managerId)) {
						OAException.throwWithMessage("部门经理【?】不存在",managerId);
					}
					manager=new EmployeeEntity();
					manager.setId(managerId);
				}
				// 2.3 存入数据库
				DepartmentEntity t=new DepartmentEntity();
				t.setCode(code);
				t.setCreateTime(Instant.now());
				t.setId(DBUtil.uuid());
				t.setManager(manager);
				t.setMembers(0);
				t.setName(name);
				t.setParent(parent);
				t.setRemark(remark);
				dao.insert(t);
				return t;
	}
	@Override
	public List<DepartmentEntity> loadAllDepartment() throws Exception {
		DepartmentDao dao=DaoFactory.getDao(DepartmentDao.class);
		List<DepartmentEntity> depts=dao.selectAll("code");
		return depts;
	}
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
		return null;
	}

	@Override
	public EmployeeEntity modifyEmployee(String id, EmployeeAo ao) throws Exception {
		return null;
	}

	@Override
	public void resetEmployeePassword(String id) throws Exception {

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