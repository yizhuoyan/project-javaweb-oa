package com.neusoft.oa.organization.entity;
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
		switch (id) {
		case 0:
			return MarriageState.UNMARRIED;
		case 1:
			return MarriageState.DIVORCED;
		case 2:
			return MarriageState.MARRIED;
		case 9:
			return MarriageState.WIDOWED;
		}
		return null;
	}
}
