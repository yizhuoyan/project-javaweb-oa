package com.neusoft.oa.organization.dto;

import java.time.temporal.ChronoField;
import java.util.Optional;

import com.neusoft.oa.core.dto.VOMap;
import com.neusoft.oa.organization.entity.DepartmentEntity;

public class DepartmentDto extends VOMap {
	
	public DepartmentDto(int initialCapacity) {
		super(initialCapacity);
	}

	static public VOMap of(DepartmentEntity d) {
		return VOMap.of(8)
				.put("id", d.getId())
				.put("code", d.getCode())
				.put("name", d.getName())
				.put("members", d.getMembers())
				.put("remark", d.getRemark())
				.put("createTime",Optional.ofNullable(d.getCreateTime())
						.map(ins->ins.getEpochSecond()*1000
						).orElse(null))
				.put("manager", Optional.ofNullable(d.getManager())
						.map(m->VOMap.of(2).put("id", m.getId())
								.put("name", m.getName())
						).orElse(null))
				.put("parent",Optional.ofNullable(d.getParent())
						.map(p->VOMap.of(2)
								.put("id", p.getId())
								.put("name",p.getName())
								)
						.orElse(null));
	}
}
