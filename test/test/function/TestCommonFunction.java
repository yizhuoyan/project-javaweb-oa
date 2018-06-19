/**
 * 
 */
package test.function;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.neusoft.oa.common.function.CommonFunction;
import com.neusoft.oa.system.entity.SysModuleEntity;

/**
 * @author Administrator
 *
 */
public class TestCommonFunction {
	static private CommonFunction fun;
	static private void loadConfigFile() {
		final String path="/system.config";
		try {
			System.getProperties().load(TestCommonFunction.class.getResourceAsStream(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@BeforeClass
	public static void before() {
		loadConfigFile();
		//fun=FunctionFactory.getFunction(CommonFunction.class);
	}
	@Test
	public void testLoadUserModule()throws Exception{
		String userId="1";
		List<SysModuleEntity> result=fun.loadUserModule(userId);
	}
}
