/**
 * shidao
 * FunctionTempldate.java
 * 2016年1月20日
 */
package com.neusoft.oa.core.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import com.neusoft.oa.core.OAException;


/**
 * 数据验证工具类
 *
 * @author root@yizhuoyan.com
 */

public class AssertThrowUtil{


static private void throwException(String message, Object... args){
  OAException.throwWithMessage(message, args);
}



/**
 * 
 * 
 * @param message
 * @param s
 * @return
 */
public static String $(String label, String s,Object...args) {
	return assertNotBlank(label+"不能为空", s, args);
}


static public void assertNull(String message, Object obj, Object... args){
  if(obj!=null) throwException(message, args);
}

static public void assertNotNull(String message, Object obj, Object... args){
  if(obj==null)
    throwException(message, args);
}

/**
 * 断言字符串是null或者空字符串或空白字符串
 *
 * @param message
 * @param s
 * @param args
 */
static public void assertBlank(String message, String s, Object... args){
  if(s!=null&&s.trim().length()!=0){
    throwException(message, args);
  }
}

static public String assertNotBlank(String message, String s,
                                    Object... args){
  if(s==null||(s = s.trim()).length()==0){
    throwException(message, args);
  }
  return s;
}


static public int assertInteger(String message, String intStr,
                                Object... args){
  if(intStr==null||intStr.trim().length()==0){
    throwException(message, args);
  }
  try{
    return Integer.parseInt(intStr);
  }catch(Exception e){
    throwException(message, args);
  }
  return 0;
}

static public void assertNotEquals(String message, Object a, Object b,
                                   Object... args){
  if(a==null ? null==b : a.equals(b)){
    throwException(message, args);
  }
}

static public void assertEquals(String message, Object a, Object b,
                                Object... args){
  if(a==null ? null!=b : !a.equals(b)){
    throwException(message, args);
  }
}

static public void assertEmpty(String message, Collection b,
                                     Object... args){
  if(b==null||b.size()!=0){
    throwException(message, args);
  }
}

static public void assertNotEmpty(String message, Collection b,
                                        Object... args){
  if(b!=null&&b.size()==0){
    throwException(message, args);
  }
}

static public void assertTrue(String message, boolean b, Object... args){
  if(!b){
    throwException(message, args);
  }
}

static public void assertFalse(String message, boolean b, Object... args){
  if(b){
    throwException(message, args);
  }
}

static public void assertLessThan(String label,int len,int target,Object... args ) {
	if(len>=target) {
		throwException(label+"必须小于"+target,args);
	}
}
static public void assertGreatThan(String label,int len,int target,Object... args ) {
	if(len<=target) {
		throwException(label+"必须大于"+target, args);
	}
}
static public void assertLessThan(String label,String s,int target,Object... args ) {
	int len=s==null?0:s.length();
	if(len>=target) {
		throwException(label+"长度不可超过"+target,args);
	}
}
static public void assertGreatThan(String label,String s,int target,Object... args ) {
	int len=s==null?0:s.length();
	if(len<=target) {
		throwException(label+"长度不可小于"+target, args);
	}
}

static public void assertIn(String label,Object target,Object[] chioces,Object... args ) {
	boolean contains=false;
	for(Object choice:chioces) {
		if(Objects.equals(target, choice)) {
				contains=true;
				break;
		}
	}
	if(!contains) {
		throwException(label+"的值必须从"+Arrays.toString(chioces)+"中选取", args);
	}
}

public static void assertAllWordCharacter(String label,String target) {
	char c=0;
	for(int i=target.length();i-->0;) {
		c=target.charAt(i);
		if((c>='0'&&c<='9')||
			(c>='a'&&c<='z')||
			(c>='A'&&c<='Z')||
			c=='-') {
		}else {
			throwException(label+"仅能是字母数字或下划线");	
		}
	}
	
}



}
