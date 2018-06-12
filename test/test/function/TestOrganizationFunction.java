package test.function;

import org.junit.BeforeClass;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.document.ao.DocumentAo;
import com.neusoft.oa.document.entity.DocumentEntity;
import com.neusoft.oa.document.function.DocumentFunction;
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
		ao.setName("测试123");
		ao.setProperty("dsf");
		ao.setRemark("sss");
		ao.setPath("123");
		
//		DocumentFunctionImpl dd=new DocumentFunctionImpl();
//		DocumentEntity d=dd.addDocument("87017b45465867eabbd450109bdc18e6", ao);
//		dd.modifyDocument("2b773d29242e89aa6e6418845391e8cc", ao);
//		dd.deleteDocument("2b773d29242e89aa6e6418845391e8cc");
		DocumentFunction fun=FunctionFactory.getFunction(DocumentFunction.class);
		DocumentEntity e = fun.loadDocumentMessage("f148c10091cb87e8fc34a0359b5b55ac");
		System.out.println(e.getFlag());
		
	}
}
