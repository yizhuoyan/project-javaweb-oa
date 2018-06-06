package test.function;

import org.junit.BeforeClass;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.organization.entity.EmployeeEntity;
import com.neusoft.oa.organization.function.OrganizationFunction;

public class TestOrganizationFunction {
	static private void loadConfigFile() {
		final String path="/app.config";
		try {
			System.getProperties().load(TestCommonFunction.class.getResourceAsStream(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		loadConfigFile();
		OrganizationFunction fun=FunctionFactory.getFunction(OrganizationFunction.class);
		String key=null;
		
		PaginationQueryResult<EmployeeEntity> listEmployee = fun.listEmployee(key, 1, 10);
		System.out.println(listEmployee);
	}
}
