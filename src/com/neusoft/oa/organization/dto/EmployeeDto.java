package com.neusoft.oa.organization.dto;

import java.util.Optional;

import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.organization.entity.DepartmentEntity;
import com.neusoft.oa.organization.entity.EmployeeEntity;

public class EmployeeDto {
	
	static public VOMap of(EmployeeEntity d) {
		return VOMap.of(25)
				.put("id", d.getId())
				.put("account", d.getAccount())
				.put("address", d.getAddress())
				.put("age", d.getAge())
				.put("male", d.isMale())
				.put("avatar", d.getAvatar())
				.put("birthday", d.getBirthday())
				.put("createTime", d.getCreateTime())
				.put("department", Optional.ofNullable(d.getDepartment())
						.map(p->VOMap.of(2)
								.put("id", p.getId())
								.put("name", p.getName())
								).orElse(null))
				.put("domicilePlace", d.getDomicilePlace())
				.put("hiredate", d.getHiredate())
				.put("homePhone", d.getHomePhone())
				.put("idcard", d.getIdcard())
				.put("lastLoginIp", d.getLastLoginIP())
				.put("lastLoginTime", d.getLastLoginTime())
				.put("lastModPasswordTime", d.getLastModPasswordTime())
				.put("marriageState", d.getMarriageState())
				.put("name", d.getName())
				.put("nationality",d.getNationality())
				.put("nativePlace",d.getNativePlace())
				.put("phone",d.getPhone())
				.put("politicalStatus",d.getPoliticalStatus())
				.put("remark",d.getRemark())
				.put("securityEmail",d.getSecurityEmail())
				.put("workPhone",d.getWorkPhone())
				.put("workEmail",d.getWorkEmail());
	}
}
