/**
*@author 吴致宇
*/

package com.neusoft.oa.attendance.dto;

import java.util.Optional;

import com.neusoft.oa.attendance.entity.AdminSetDateEntity;
import com.neusoft.oa.core.dto.VOMap;

public class AdminSetDateDto extends VOMap{

	public AdminSetDateDto(int initialCapacity) {
		super(initialCapacity);
		
	}
	static public VOMap of(AdminSetDateEntity sde) {
		return VOMap.of(10)
				.put("id", sde.getId())
				.put("whenDay", sde.getWhenDay())
				.put("workDay", sde.getWorkDay())
				.put("onDuty", sde.getOnDuty())
				.put("offDuty", sde.getOffDuty())
				.put("signInStart", sde.getSignInStart())
				.put("signInEnd", sde.getSignInEnd())
				.put("signOutStart", sde.getSignOutStart())
				.put("signOutEnd", sde.getSignOutEnd())
				.put("remark", sde.getRemark());
	}
}
