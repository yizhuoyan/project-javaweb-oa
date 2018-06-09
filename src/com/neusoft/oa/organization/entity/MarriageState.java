package com.neusoft.oa.organization.entity;

import java.util.Arrays;

/**
 * 婚姻状态
 * @author yizhouyan
 *
 */
public enum MarriageState {
	UNMARRIED(0),
	MARRIED(2),
	DIVORCED(1),
	WIDOWED(9);
	
	final public int id;
	
	private MarriageState(int id) {
		this.id=id;
	}
	public static MarriageState of(int id) {
		return Arrays.stream(MarriageState.values()).
		filter(m->m.id==id).findFirst().orElse(null);
	}
}
