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
import java.util.Optional;

import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dictionary.Dictionary;
import com.neusoft.oa.core.dto.PaginationQueryResult;
<<<<<<< HEAD

import com.neusoft.oa.core.service.PinYinService;

=======
import com.neusoft.oa.core.service.PinYinService;
>>>>>>> 1dcd7aea9ecd9138c6c579166fae754679401074
import com.neusoft.oa.core.util.IDCard;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.organization.ao.DepartmentAo;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.dao.DepartmentDao;
import com.neusoft.oa.organization.dao.EmployeeDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

public class OrganizationFunctionImpl extends ThisSystemUtil implements OrganizationFunction {
	EmployeeDao employeeDao = DaoFactory.getDao(EmployeeDao.class);
	DepartmentDao departmentDao = DaoFactory.getDao(DepartmentDao.class);
	
	@Override
<<<<<<< HEAD

	public String generateEmployyWorkEmail(String name,String nativePlace) throws Exception {
=======
	public String generateEmployyWorkEmail(String name) throws Exception {
>>>>>>> 1dcd7aea9ecd9138c6c579166fae754679401074
		name=$("姓名",name);
		String namePinyin=PinYinService.pinyin(name);
		String emailAddress=System.getProperty("oa.default-work-email","@neusoft.com");
		
		String workEmail=namePinyin+emailAddress;
		//判断是否有同名
		if(employeeDao.exist("workEmail", workEmail)) {
			//则添加序号
			int likeCount=employeeDao.selectWorkEmailLikeCount(namePinyin,emailAddress);
			workEmail=namePinyin+likeCount+emailAddress;
			while(employeeDao.exist("workEmail", workEmail)) {
				likeCount++;
				workEmail=namePinyin+likeCount+emailAddress;
			}
		}
		return workEmail;
	}
	@Override
<<<<<<< HEAD

=======
>>>>>>> 1dcd7aea9ecd9138c6c579166fae754679401074
	public String generateNextEmployeeAccount(String departmentId, String hiredate) throws Exception {
		departmentId=$("部门id",departmentId);
		hiredate=$("入职日期",hiredate);
		DepartmentEntity department = departmentDao.select("id", departmentId);
		
		assertNotNull("部门不存在", department);
<<<<<<< HEAD



=======
>>>>>>> 1dcd7aea9ecd9138c6c579166fae754679401074
		//规则入职时间（6位）+部门编号（6位）+3位序号+1位随机数字=共16
		StringBuilder result=new StringBuilder();
		result.append(hiredate.replaceAll("-",""));
		result.append(prefixFill(department.getCode(),6,'0'));
		result.append(prefixFill(department.getMembers()+1, 3,'0'));
		result.append((int)(Math.random()*10));
<<<<<<< HEAD

=======
>>>>>>> 1dcd7aea9ecd9138c6c579166fae754679401074
		return result.toString();
	}
	@Override
	public EmployeeEntity modifyEmployee(String id, EmployeeAo ao) throws Exception {
		// 1验证参数
		id=$("id",id);
		EmployeeEntity old=employeeDao.select("id", id);
		if(old==null) {
			//当成新增
			return this.addEmployee(ao);
		}
		Map<String,Object> updateMap=new HashMap<>();
		
		String name = $("姓名", ao.getName());
		if(!Objects.equals(name, old.getName())) {
			assertLessThan("姓名", name, 16);
			updateMap.put("name", name);
			old.setName(name);
		}
		

		String idcardString = $("身份证号", ao.getIdcard());
		if(!Objects.equals(idcardString, old.getIdcard())) {
			IDCard idcard = null;
			try {
				idcard = IDCard.of(idcardString);
			} catch (Exception e) {
				OAException.throwWithMessage("身份证格式不正确");
			}
			updateMap.put("idcard", idcardString);
			updateMap.put("sex", idcard.isMale());
			updateMap.put("age", idcard.getAge());
			updateMap.put("birthday", idcard.getBirthDay());
			old.setIdcard(idcardString);
			old.setAge(idcard.getAge());
			old.setMale(idcard.isMale());
			old.setBirthday(idcard.getBirthDay());
		}
		String homePhone = $("家庭电话", ao.getHomePhone());
		if(!Objects.equals(homePhone, old.getHomePhone())) {
			assertLessThan("家庭电话", homePhone, 32);
			updateMap.put("homePhone", homePhone);
			old.setHomePhone(homePhone);
		}
		String nativePlace = $("籍贯", ao.getNativePlace());
		if(!Objects.equals(nativePlace, old.getNativePlace())) {
			assertLessThan("籍贯", homePhone, 64);
			updateMap.put("nativePlace", nativePlace);
			old.setNativePlace(nativePlace);
		}
		String domicilePlace = $("户口所在地", ao.getDomicilePlace());
		if(!Objects.equals(domicilePlace, old.getDomicilePlace())) {
			assertLessThan("户口所在地", homePhone, 64);
			updateMap.put("domicilePlace", domicilePlace);
			old.setDomicilePlace(domicilePlace);
		}

		String nationality = $("民族", ao.getNationality());
		if(!Objects.equals(nationality, Integer.toString(old.getNationality()))) {
			if (!Dictionary.of("nationality").contains(nationality)) {
				OAException.throwWithMessage("非法民族值");
			}
			int nationalityInt = parseInt(nationality);
			updateMap.put("nationality", nationalityInt);
			old.setNationality(nationalityInt);
		}

		String politicalStatus = $("政治面貌", ao.getPoliticalStatus());
		if(!Objects.equals(politicalStatus, Integer.toString(old.getPoliticalStatus()))) {
			if (!Dictionary.of("political-status").contains(politicalStatus)) {
				OAException.throwWithMessage("非法政治面貌值");
			}
			int politicalStatusInt = parseInt(politicalStatus);
			updateMap.put("politicalStatus", politicalStatusInt);
			old.setPoliticalStatus(politicalStatusInt);
		}
		
		String marriageState = $("婚姻状况", ao.getMarriageState());
		if(!Objects.equals(marriageState, Integer.toString(old.getMarriageState()))) {
			if (!Dictionary.of("marital-status").contains(politicalStatus)) {
				OAException.throwWithMessage("非法婚姻状况值");
			}
			int marriageStateInt = parseInt(marriageState);
			updateMap.put("marriageState", marriageStateInt);
			old.setPoliticalStatus(marriageStateInt);
		}
		
		String address = $("现居住地", ao.getAddress());
		if(!Objects.equals(address, old.getAddress())) {
			assertLessThan("现居住地", address, 128);
			updateMap.put("address", address);
			old.setAddress(address);
		}

		// 入职信息
		String departmentId = $("入职部门", ao.getDepartmentId());
		String oldDepartmentId=old.getDepartment().getId();
		if(!Objects.equals(departmentId, oldDepartmentId)) {
				if (!departmentDao.exist("id", departmentId)) {
					OAException.throwWithMessage("入职部门值非法");
				}
				DepartmentEntity newDepartment=new DepartmentEntity();
				newDepartment.setId(departmentId);
				old.setDepartment(newDepartment);
				//新部门+1
				departmentDao.updateDepartmentMembers(departmentId, 1);
				//旧部门-1
				departmentDao.updateDepartmentMembers(oldDepartmentId, -1);
			
				updateMap.put("department_Id", departmentId);
			
		}
		
		String hiredate = $("入职日期", ao.getHiredate());
		LocalDate hiredateUse = null;
		try {
			hiredateUse = LocalDate.parse(hiredate);
		} catch (DateTimeParseException e) {
			OAException.throwWithMessage("入职日期格式错误，格式为yyyy-MM-dd");
		}
		
		if(!Objects.equals(hiredateUse, old.getHiredate())) {
			updateMap.put("hiredate", hiredateUse);
			old.setHiredate(hiredateUse);
		}

		
		//工号不能修改
		

		String workPhone = $("办公电话", ao.getWorkPhone());
		if(!Objects.equals(workPhone, old.getWorkPhone())) {
			assertLessThan("办公电话", address, 32);
			updateMap.put("workPhone", workPhone);
		}
		
		//工作邮箱不能修改（单独修改）

		String remark = trim(ao.getRemark());
		if(!Objects.equals(remark, old.getRemark())) {
			if (remark != null) {
				assertLessThan("工作邮箱", remark, 128);
			}
			updateMap.put("remark", remark);
		}
		// 2执行业务逻辑
		employeeDao.update(id, updateMap);
		
		// 3组装业务结果
		return old;
	}

	@Override
	public void deleteEmployee(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑

		employeeDao.delete("id", id);
	}

	@Override
	public EmployeeEntity loadEmployee(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑
		EmployeeEntity e = employeeDao.select("id", id);
		// 2.1 验证id
		if (e == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}

		// 2.2 查询入职部门
		DepartmentEntity department = e.getDepartment();
		if (department != null) {
			department = departmentDao.select("id", department.getId());
			e.setDepartment(department);
		}
		// 3组装业务结果
		return e;
	}

	@Override
	public void deleteDepartment(String id) throws Exception {
		id = $("id", id);
		DepartmentEntity d = departmentDao.select("id", id);
		assertNotNull("部门{1}不存在或已被删除", d, id);
		// 1不能入职员工
		if (d.getMembers() > 0) {
			OAException.throwWithMessage("此部门已有入职员工，不能删除");
		}
		// 2不能包含子部门
		if (departmentDao.existsChildren(id)) {
			OAException.throwWithMessage("此部门包含子部门，不能删除");
		}
		// 3执行删除
		departmentDao.delete("id", id);

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
		DepartmentEntity old = departmentDao.select("id", id);
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
				newParent = departmentDao.select("id", parentId);
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
				newParent = departmentDao.select("id", oldParentId);
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
			if (departmentDao.exist("code", code)) {
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
				if (!employeeDao.exist("uid", managerId)) {
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
		departmentDao.update(id, needUpdate);
		return old;
	}

	@Override
	public List<DepartmentEntity> loadCanBeParentList(String id) throws Exception {
		id = trim(id);
		if (id != null) {
			// 找到对应code
			DepartmentEntity excludeDept = departmentDao.select("id", id);
			assertNotNull("id{1}无效", excludeDept, id);
			String treeCode = excludeDept.getCode();
			List<DepartmentEntity> result = departmentDao.selectsExcludeWithDescendantByTreeCode(treeCode);
			return result;
		} else {
			return departmentDao.selectAll("code");
		}
	}

	@Override
	public DepartmentEntity loadDepartment(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑
		DepartmentEntity e = departmentDao.select("id", id);
		// 2.1 验证id
		if (e == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		// 2.2 查询出父模块
		DepartmentEntity parent = e.getParent();
		if (parent != null) {
			parent = departmentDao.select("id", parent.getId());
			e.setParent(parent);
		}
		// 2.3 查询部门经理
		EmployeeEntity manager = e.getManager();
		if (manager != null) {
			manager = employeeDao.select("id", manager.getId());
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
		// 2.1验证父模块是否存在
		DepartmentEntity parent = null;
		if (parentId != null) {
			parent = departmentDao.select("id", parentId);
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
		if (departmentDao.exist("code", code)) {
			OAException.throwWithMessage("代号【?】已存在", code);
		}

		// 2.3 部门经理
		EmployeeEntity manager = null;
		if (managerId != null) {
			
			if (!employeeDao.exist("id", managerId)) {
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
		departmentDao.insert(t);
		return t;
	}

	@Override
	public List<DepartmentEntity> loadAllDepartment() throws Exception {
		
		List<DepartmentEntity> depts = departmentDao.selectAll("code");
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
		if (!Dictionary.of("political-status").contains(politicalStatus)) {
			OAException.throwWithMessage("非法政治面貌值");
		}
		int politicalStatusInt = parseInt(politicalStatus);

		String marriageState = $("婚姻状况", ao.getMarriageState());
		if (!Dictionary.of("marital-status").contains(marriageState)) {
			OAException.throwWithMessage("非法婚姻状况值");
		}
		int marriageStateInt = parseInt(marriageState);

		String address = $("现居住地", ao.getAddress());
		assertLessThan("现居住地", address, 128);

		// 入职信息
		String departmentId = $("入职部门", ao.getDepartmentId());
		if (!departmentDao.exist("id", departmentId)) {
			OAException.throwWithMessage("入职部门值非法");
		}

		String hiredate = $("入职日期", ao.getHiredate());
		LocalDate hiredateUse = null;
		try {
			hiredateUse = LocalDate.parse(hiredate);
		} catch (DateTimeParseException e) {
			OAException.throwWithMessage("入职日期格式错误，格式为yyyy-MM-dd");
		}

		String account = $("员工工号", ao.getAccount());
		if (employeeDao.exist("account", account)) {
			OAException.throwWithMessage("员工工号{1}已存在，请切换", account);
		}

		String workPhone = $("办公电话", ao.getWorkPhone());
		assertLessThan("办公电话", address, 32);

		String workEmail = $("工作邮箱", ao.getWorkEmail());
<<<<<<< HEAD
		assertLessThan("工作邮箱", address, 16);
		//必须是数字和字母
		assertAllWordCharacter("工作邮箱", workEmail);

		
		String mailSuffix=System.getProperty("oa.default-work-email","@nuesoft.com");
		

		

		if(!workEmail.endsWith(mailSuffix)) {
			workEmail=workEmail+mailSuffix;
		}
		
=======
		assertLessThan("工作邮箱", address, 32);
		assertIsEmail("工作邮箱",workEmail);
>>>>>>> 1dcd7aea9ecd9138c6c579166fae754679401074
		if (employeeDao.exist("workemail", workEmail)) {
			OAException.throwWithMessage("工作邮箱{1}已存在，请切换", workEmail);
		}

		String remark = trim(ao.getRemark());
		if (remark != null) {
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
		DepartmentEntity department = new DepartmentEntity();
		department.setId(departmentId);
		e.setDepartment(department);
		e.setNationality(nationality);
		e.setNativePlace(nativePlace);

		e.setDomicilePlace(domicilePlace);
		e.setMale(idcard.isMale());
		e.setBirthday(idcard.getBirthDay());
		e.setMarriageState(marriageStateInt);
		e.setName(name);
		e.setAccount(account);
		String defaultPassword=idcardString;
		e.setPassword(ThisSystemUtil.md5(defaultPassword));
		e.setCreateTime(new Date());
		e.setPhone(null);
		e.setSecurityEmail(null);
		e.setAvatar(null);
		e.setRemark(remark);
		e.setFlag(EmployeeEntity.FLAG_NORMAL);
		//添加员工
		employeeDao.insert(e);
		//更新员工部门人数
		if(departmentId!=null) {
			departmentDao.updateDepartmentMembers(departmentId, 1);
		}
		
		// 3组装业务结果
		return e;
	}

	@Override
	public List<EmployeeEntity> addEmployee(List<EmployeeAo> ao) throws Exception {
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
		int total = employeeDao.selectsByKey(key, pageNo, pageSize, pageData);
		// 3组装业务结果
		PaginationQueryResult<EmployeeEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		return result;
	}

}