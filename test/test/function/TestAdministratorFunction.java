package test.function;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.neusoft.oa.base.entity.SysModuleEntity;
import com.neusoft.oa.base.function.AdministratorFunction;
import com.neusoft.oa.base.function.impl.AdministratorFunctionImpl;
import com.neusoft.oa.base.web.servlet.administrator.sysmodule.ao.SysModuleAo;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dto.QueryResult;


public class TestAdministratorFunction {
	static AdministratorFunction fun;
	@BeforeClass
	static public void testInit() {
		System.out.println("测试计划开始执行此方法，仅执行一次");
		fun=new AdministratorFunctionImpl();
	}
	@Before
	public void tesestBefore() {
		System.out.println("每个测试方法执行之前都会执行此方法");
		
	}
	@After
	public void teseteAfter() {
		System.out.println("每个测试方法执行结束后执行");
	}
	@AfterClass
	public static void teestEnd() {
		System.out.println("整个测试计划介绍，你想做点啥");
	}
	@Ignore
	@Test(timeout=1000,expected=OAException.class)
	public void testAddSysModule() throws Exception{
		
		SysModuleAo ao=new SysModuleAo();
		ao.setCode("xxx");
		ao.setIcon(null);
		ao.setName("xxx管理");
		SysModuleEntity e=fun.addSysModule(ao);
		
		SysModuleEntity result=fun.loadSysModule(e.getId());
		
		Assert.assertNotNull("新增失败", result);
		
	}
	@Test
	public  void testQuerySysModuleByKey() throws Exception{
		String key="xxx管理";
		QueryResult<SysModuleEntity> result= fun.querySysModuleByKey(key);
		Assert.assertEquals("查询失败",1, result.getTotalRows());
	}
	
	@Test
	public void testModifySysModule() throws Exception{
		String id="167d398da435ddda9544f41d7854baea";
		SysModuleAo ao=new SysModuleAo();
		ao.setCode("0922");
		ao.setIcon(null);
		ao.setName("管理xx");
		fun.modifySysModule(id, ao);
	}
	
}
