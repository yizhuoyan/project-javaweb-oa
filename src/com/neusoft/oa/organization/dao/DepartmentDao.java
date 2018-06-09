package com.neusoft.oa.organization.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public interface DepartmentDao extends TemplateDao<DepartmentEntity>{
	/**
	 * 选择除treeCode部门及其子部门的其余部门
	 * @param treeCode 部门的code
	 * @return
	 * @throws Exception
	 */
	List<DepartmentEntity> selectsExcludeWithDescendantByTreeCode(String treeCode)throws Exception;
	/**
	 * 判断部门是否存在子部门
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean existsChildren(String id)throws Exception;
	
}
