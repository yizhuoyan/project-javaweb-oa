package com.neusoft.oa.organization.function;

import java.util.List;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.organization.ao.DepartmentAo;
import com.neusoft.oa.organization.ao.EmployeeAo;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public interface OrganizationFunction {

	String generateEmployyWorkEmail(String name,String nativePlace)throws Exception;
	/**
	 * 分配员工工号
	 * @param departmentId
	 * @param hiredate
	 * @return
	 * @throws Exception
	 */

	String generateNextEmployeeAccount(String departmentId,String hiredate)throws Exception;
	EmployeeEntity loadEmployee(String id)throws Exception;
	void deleteEmployee(String id)throws Exception;
	/**
	 * 删除部门，仅允许删除无员工和无子部门的部门
	 * @param id 
	 * @throws Exception
	 */
	void deleteDepartment(String id)throws Exception;
	
	DepartmentEntity modifyDepartment(String id,DepartmentAo ao)throws Exception;
	
	DepartmentEntity loadDepartment(String id)throws Exception;
	/**
	 * 新增一个员工
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	EmployeeEntity addEmployee(EmployeeAo ao)throws Exception;
	/**
	 * 新增部门
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	DepartmentEntity addDepartment(DepartmentAo ao)throws Exception;
	/**
	 * 批量录入员工
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	List<EmployeeEntity> addEmployee(List<EmployeeAo> ao)throws Exception;
	/**
	 * 
	 * @param id
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	EmployeeEntity modifyEmployee(String id,EmployeeAo ao)throws Exception;
	/**
	 * 重置员工密码
	 * @param id
	 * @throws Exception
	 */
	void resetEmployeePassword(String id)throws Exception;
	
	
	PaginationQueryResult<EmployeeEntity> listEmployee(String key,int pageNo,int pageSize)throws Exception;
	
	
	List<DepartmentEntity> loadAllDepartment()throws Exception;

	List<DepartmentEntity> loadCanBeParentList(String id)throws Exception;
}
