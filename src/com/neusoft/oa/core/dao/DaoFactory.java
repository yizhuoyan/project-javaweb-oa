/**
 * 
 */
package com.neusoft.oa.core.dao;

import java.io.DataOutput;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neusoft.oa.system.dao.impl.SysModuleDaoImpl;
import com.neusoft.oa.system.dao.impl.SysRoleDaoImpl;
import com.neusoft.oa.system.dao.impl.SysUserDaoImpl;

/**
 * @author Administrator
 *
 */
public class DaoFactory {
	static private Logger LOG=Logger.getLogger(DaoFactory.class); 
	private static final Map<Class<?>, Object> DAOMAP=new HashMap<>();
	
	
	public static void put(Class<?> type,Object dao) {
		LOG.debug("注册dao对象:"+type.getName()+"="+dao.getClass().getName());
		DAOMAP.put(type, dao);
	}
	@SuppressWarnings("unchecked")
	public static  <T> T getDao(Class<T> type){
		T target=(T)DAOMAP.get(type);
		if(target==null) {
			synchronized (DaoFactory.class) {
				if(target==null) {		
					put(type, target=createDao(type));
				}
			}
		}
		return target;
	}
	private static<T> T createDao(Class<?> interfaceType) {
		try {
			String packageName=interfaceType.getPackage().getName();
			String className=interfaceType.getSimpleName();
			String targetClassName=packageName+".impl."+className+"Impl";
			Class<?> implType=Class.forName(targetClassName);
			Constructor<?> declaredConstructor = implType.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			return (T)declaredConstructor.newInstance();
		}catch(Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
