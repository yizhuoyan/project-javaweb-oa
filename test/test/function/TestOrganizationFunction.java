package test.function;

import org.junit.BeforeClass;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.impl.DocumentFunctionImpl;
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
	static {
		loadConfigFile();
		
	}		
	public static void main(String[] args) throws Exception{
		DocumentAo ao=new DocumentAo();
//		ao.setCreateTime(Instant.now());
//		ao.setCreateUserId(new EmployeeEntity());
//		ao.setDeptId(new DepartmentEntity());
//		ao.setFlag(DOCUMENT_FLAG_NORMAL);
//		ao.setId(id);
		ao.setName("测试");
		ao.setProperty("dsf");
		ao.setRemark("fff");
		
		DocumentFunctionImpl dd=new DocumentFunctionImpl();
		DocumentEntity d=dd.addDocument("87017b45465867eabbd450109bdc18e6", ao);
	}
}
