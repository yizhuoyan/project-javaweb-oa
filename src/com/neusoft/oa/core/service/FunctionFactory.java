/**
 * 
 */
package com.neusoft.oa.core.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;

/**
 * @author Administrator
 *
 */
public class FunctionFactory {
	static private Logger LOG=Logger.getLogger(FunctionFactory.class); 
	private static Map<Class<?>, Object> FUNCTIONS_MAP = new HashMap<>();

	public static void put(Class<?> type,Object dao) {
		LOG.debug("注册function对象:"+type.getName()+"="+dao.getClass().getName());
		FUNCTIONS_MAP.put(type, dao);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getFunction(Class<T> type) {
		T target=(T)FUNCTIONS_MAP.get(type);
		if(target==null) {
			synchronized (DaoFactory.class) {
				if(target==null) {
					target=createFunction(type);
					//事务管理
					target=transcationProxy(target);
					put(type, target);
				}
			}
		}
		return target;
	}
	
	@SuppressWarnings("unchecked")
	private static<T> T createFunction(Class<?> interfaceType) {
		try {
			String packageName=interfaceType.getPackage().getName();
			String className=interfaceType.getSimpleName();
			String targetClassName=packageName+".impl."+className+"Impl";
			Class<?> implType=Class.forName(targetClassName.trim());
			Constructor<?> declaredConstructor = implType.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			return (T)declaredConstructor.newInstance();
		}catch(Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("all")
	private static <T> T transcationProxy(T t) {
		return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object result = null;
						StringBuilder message=new StringBuilder();
						try {
							message.append("执行").append(method.toGenericString());
							//非公开，静态方法不代理
							if(false==Modifier.isPublic(method.getModifiers())
									||true==Modifier.isStatic(method.getModifiers())
									){
								return method.invoke(t, args); 
							}
							//自定义事务管理的方法不代理,规则方法名最后为ByCTM（Custom transaction management）
							if(method.getName().endsWith("byCTM")) {
								return method.invoke(t, args);
							}
							//其他默认开启事务
							//判断是否开启了事务
							if(DBUtil.getConnection().getAutoCommit()) {//未开启
								message.append("未开启事务");
								DBUtil.getConnection().setAutoCommit(false);
								//执行方法，
								result = method.invoke(t, args);
								//提交
								DBUtil.getConnection().commit();
							}else {//已开启
								message.append("已开启事务");
								//执行方法，不提交
								result = method.invoke(t, args);
							}
						} catch (Throwable e) {
							DBUtil.getConnection().rollback();
							throw e.getCause();
						}finally {
							LOG.trace(message);
						}
						return result;
					}
				});
	}
}
