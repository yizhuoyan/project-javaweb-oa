package com.neusoft.oa.organization.dao;

import java.util.List;

import com.neusoft.oa.core.dao.TemplateDao;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public interface DepartmentDao extends TemplateDao<DepartmentEntity>{

	List<DepartmentEntity> selectsExcludeWithDescendantByTreeCode(String treeCode)throws Exception;
}
