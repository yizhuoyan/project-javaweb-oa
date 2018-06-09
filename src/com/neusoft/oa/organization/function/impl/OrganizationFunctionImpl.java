package com.neusoft.oa.organization.function.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.neusoft.oa.base.dao.SysModuleDao;
import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dictionary.Dictionary;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.util.AssertThrowUtil;
import com.neusoft.oa.core.util.IDCard;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.ao.DepartmentAo;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.entity.MarriageState;
import com.neusoft.oa.organization.function.OrganizationFunction;

public class OrganizationFunctionImpl extends ThisSystemUtil implements OrganizationFunction {

	@Override
	public void deleteDepartment(String id) throws Exception {
		id = $("id", id);
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		DepartmentEntity d = dao.select("id", id);
		assertNotNull("部门{1}不存在或已被删除", d, id);
		// 1不能入职员工
		if (d.getMembers() > 0) {
			OAException.throwWithMessage("此部门已有入职员工，不能删除");
		}
		// 2不能包含子部门
		if (dao.existsChildren(id)) {
			OAException.throwWithMessage("此部门包含子部门，不能删除");
		}
		// 3执行删除
		dao.delete("id", id);

	}

	@Override
	public DepartmentEntity modifyDepartment(String id, DepartmentAo ao) throws Exception {
		// 1清理验证参数
		id = $("id", id);
		String code = $("代号", ao.getCode());
		String name = $("名字", ao.getName());
		String parentId = trim(ao.getParentId());
		String managerId = trim(ao.getManagerId());
		String remark = trim(ao.getRemark());
		// 2执行业务逻辑
		// 2.1 查询出旧数据
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		DepartmentEntity old = dao.select("id", id);
		// 2.2 验证数据id是否有效
		if (old == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		Map<String, Object> needUpdate = new HashMap<>(8, 1);
		// 2.3父部门
		DepartmentEntity newParent = null;
		if (!Objects.equals(parentId, old.getParentId())) {
			// 父部门不能是本身
			if (Objects.equals(parentId, id)) {
				OAException.throwWithMessage("父部门不能是本身");
			}
			if (parentId != null) {
				newParent = dao.select("id", parentId);
			}
			// 父部门不能是当前部门的后代
			if (newParent.getCode().startsWith(old.getCode())) {
				OAException.throwWithMessage("父部门不能是当前部门的后代");
			}
			needUpdate.put("parent_id", parentId);
			old.setParent(newParent);
		} else {
			String oldParentId = old.getParentId();
			if (oldParentId != null) {
				newParent = dao.select("id", oldParentId);
			}
		}

		// 2.4 代号
		if (!code.equals(old.getCode())) {
			// 说明修改代号
			String parentCode = newParent == null ? "" : newParent.getCode();
			// 长度
			if (code.length() - 2 != parentCode.length()) {
				OAException.throwWithMessage("代号非法", parentId);
			}
			// 前面部分必须和父代号一致
			if (!code.startsWith(parentCode)) {
				OAException.throwWithMessage("代号非法", parentId);
			}
			// 2.2.1 新代号不能存在
			if (dao.exist("code", code)) {
				OAException.throwWithMessage("代号【?】已存在", code);
			}
			// 代号符合规范
			old.setCode(code);
			needUpdate.put("code", code);
		}
		// 2.3 名字
		if (!name.equals(old.getName())) {
			assertLessThan("名字不能超过?个字符", name.length(), 32);
			needUpdate.put("name", name);
			old.setName(name);
		}
		// 2.4 部门经理
		if (!Objects.equals(managerId, old.getManagerId())) {
			EmployeeEntity newManager = null;
			if (managerId != null) {
				EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
				if (!edao.exist("uid", managerId)) {
					OAException.throwWithMessage("部门经理【?】不存在", managerId);
				}
				newManager = new EmployeeEntity();
				newManager.setId(managerId);
			}
			needUpdate.put("manager_id", managerId);
			old.setManager(newManager);
		}
		// 2.4 其他
		if (!Objects.equals(remark, old.getRemark())) {
			needUpdate.put("remark", remark);
			old.setRemark(remark);
		}
		// 2.5 进行数据库更新
		dao.update(id, needUpdate);
		return old;
	}

	@Override
	public List<DepartmentEntity> loadCanBeParentList(String id) throws Exception {
		id = trim(id);
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		if (id != null) {
			// 找到对应code
			DepartmentEntity excludeDept = dao.select("id", id);
			assertNotNull("id{1}无效", excludeDept, id);
			String treeCode = excludeDept.getCode();
			List<DepartmentEntity> result = dao.selectsExcludeWithDescendantByTreeCode(treeCode);
			return result;
		} else {
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
		DepartmentEntity parent = e.getParent();
		if (parent != null) {
			parent = dao.select("id", parent.getId());
			e.setParent(parent);
		}
		// 2.3 查询部门经理
		EmployeeEntity manager = e.getManager();
		if (manager != null) {
			EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
			manager = edao.select("id", manager.getId());
			e.setManager(manager);
		}
		// 3组装业务结果
		return e;
	}

	@Override
	public DepartmentEntity addDepartment(DepartmentAo ao) throws Exception {
		// 1 清理验证参数
		String parentId = trim(ao.getParentId());
		String managerId = trim(ao.getManagerId());
		String code = $("代号", ao.getCode());
		String name = $("名字", ao.getName());
		String remark = trim(ao.getRemark());
		// 2 执行业务逻辑
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		// 2.1验证父模块是否存在
		DepartmentEntity parent = null;
		if (parentId != null) {
			parent = dao.select("id", parentId);
			if (parent == null) {
				OAException.throwWithMessage("父部门【?】不存在", parentId);
			}
		}
		// 2.2代号
		String parentCode = parent == null ? "" : parent.getCode();
		// 长度
		if (code.length() - 2 != parentCode.length()) {
			OAException.throwWithMessage("代号非法", parentId);
		}
		// 前面部分必须和父代号一致
		if (!code.startsWith(parentCode)) {
			OAException.throwWithMessage("代号非法", parentId);
		}
		// 不能重复
		if (dao.exist("code", code)) {
			OAException.throwWithMessage("代号【?】已存在", code);
		}

		// 2.3 部门经理
		EmployeeEntity manager = null;
		if (managerId != null) {
			EmployeeDao edao = DaoFactory.getDao(EmployeeDao.class);
			if (!edao.exist("id", managerId)) {
				OAException.throwWithMessage("部门经理【?】不存在", managerId);
			}
			manager = new EmployeeEntity();
			manager.setId(managerId);
		}
		// 2.3 存入数据库
		DepartmentEntity t = new DepartmentEntity();
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
		DepartmentDao dao = DaoFactory.getDao(DepartmentDao.class);
		List<DepartmentEntity> depts = dao.selectAll("code");
		return depts;
	}

	@Override
	public EmployeeEntity addEmployee(EmployeeAo ao) throws Exception {
		// 1验证参数
		String name = $("姓名", ao.getName());
		assertLessThan("姓名", name, 16);
		
		String idcardString = $("身份证号", ao.getIdcard());
		IDCard idcard = null;
		try {
			idcard = IDCard.of(idcardString);
		} catch (Exception e) {
			OAException.throwWithMessage("身份证格式不正确");
		}

		String homePhone = $("家庭电话", ao.getHomePhone());
		assertLessThan("家庭电话", homePhone, 32);
		
		
		String nativePlace = $("籍贯", ao.getNativePlace());
		assertLessThan("籍贯", homePhone, 64);
		
		String domicilePlace = $("户口所在地", ao.getDomicilePlace());
		assertLessThan("户口所在地", homePhone, 64);
		
		String nationalityStr = $("民族", ao.getNationality());
		if (!Dictionary.of("nationality").contains(nationalityStr)) {
			OAException.throwWithMessage("非法民族值");
		}
		int nationality = parseInt(nationalityStr);
		
		
		String politicalStatus = $("政治面貌", ao.getPoliticalStatus());
		if(!Dictionary.of("political-status").contains(politicalStatus)) {
			OAException.throwWithMessage("非法政治面貌值");
		}
		int politicalStatusInt = parseInt(politicalStatus);

		
		String marriageState = $("婚姻状况", ao.getMarriageState());
		if(!Dictionary.of("marital-status").contains(politicalStatus)) {
			OAException.throwWithMessage("非法婚姻状况值");
		}
		int marriageStateInt = parseInt(marriageState);
		
		
		String address = $("现居住地", ao.getAddress());
		assertLessThan("现居住地", address, 128);
		
		
		// 入职信息
		String departmentId = $("入职部门", ao.getDepartmentId());
		DepartmentDao deptDao=DaoFactory.getDao(DepartmentDao.class);
		if(!deptDao.exist("id", departmentId)) {
			OAException.throwWithMessage("入职部门值非法");
		}
		
		String hiredate = $("入职日期", ao.getHiredate());
		LocalDate hiredateUse = null;
		try {
			hiredateUse = LocalDate.parse(hiredate);
		} catch (DateTimeParseException e) {
			OAException.throwWithMessage("入职日期格式错误，格式为yyyy-MM-dd");
		}
		
		EmployeeDao employeeDao=DaoFactory.getDao(EmployeeDao.class);
		String account = $("员工工号", ao.getAccount());
		if(employeeDao.exist("account", account)) {
			OAException.throwWithMessage("员工工号{1}已存在，请切换",account);
		}
		
		String workPhone = $("办公电话", ao.getWorkPhone());
		assertLessThan("办公电话", address, 32);
		
		String workEmail = $("工作邮箱", ao.getWorkEmail());
		assertLessThan("工作邮箱", address, 32);
		if(employeeDao.exist("workemail", workEmail)) {
			OAException.throwWithMessage("工作邮箱{1}已存在，请切换",workEmail);
		}
		
		
		String remark =trim(ao.getRemark());
		if(remark!=null) {
			assertLessThan("工作邮箱", remark, 128);
		}
		// 2执行业务逻辑
		EmployeeEntity e = new EmployeeEntity();
		e.setId(DBUtil.uuid());
		e.setPoliticalStatus(politicalStatusInt);
		e.setIdcard(idcardString);
		e.setAge(idcard.getAge());
		e.setAddress(ao.getAddress());
		e.setWorkEmail(workEmail);
		e.setHomePhone(homePhone);
		e.setHiredate(hiredateUse);
		e.setWorkPhone(workPhone);
		DepartmentEntity dept = new DepartmentEntity();
		dept.setId(departmentId);
		e.setDepartment(dept);
		e.setNationality(nationality);
		e.setNativePlace(nativePlace);

		e.setDomicilePlace(domicilePlace);
		e.setMale(idcard.isMale());
		e.setBirthday(idcard.getBirthDay());
		e.setMarriageState(marriageStateInt);
		e.setName(name);
		e.setAccount(account);
		e.setPassword("123456");
		e.setCreateTime(new Date());
		e.setPhone(null);
		e.setSecurityEmail(null);
		e.setAvatar(null);
		e.setRemark(remark);
		// 3组装业务结果
		EmployeeDao dao = DaoFactory.getDao(EmployeeDao.class);
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
		key = trim(key);
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
