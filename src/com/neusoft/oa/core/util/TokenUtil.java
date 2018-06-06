package com.neusoft.oa.core.util;

import java.util.Random;

public class TokenUtil {
	private static final char[] USES_CHARS="abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789".toCharArray();
	private final static Random RANDOM=new Random();
	
	public static String random(int length) {
		char[] result=new char[length];
		int len=USES_CHARS.length;
		for(int i=result.length;i-->0;) {
			result[i]=USES_CHARS[RANDOM.nextInt(len)];
		}
		return new String(result);
	}
}
