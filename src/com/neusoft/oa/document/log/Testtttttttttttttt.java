package com.neusoft.oa.document.log;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletContext;

import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.document.log.entity.DocumentLogEntity;
import com.neusoft.oa.document.log.function.DocumentLogAO;
import com.neusoft.oa.document.log.function.DocumentLogFunction;

public class Testtttttttttttttt {
	
	
	private static void loadConfigFile() {
		final String path="/app.config";
		try {
			Reader reader=new InputStreamReader(Testtttttttttttttt.class.getResourceAsStream(path), "utf-8");
			System.getProperties().load(reader);
			
		}catch(Exception e) {
			
		}
	}

public static void main(String[] args) throws Exception {
	loadConfigFile();
	DocumentLogAO ao = new DocumentLogAO();
	ao.setTarget("lxw");
	ao.setOperation("1");
	ao.setBeginoperationTime(" ");
	ao.setEndoperationTime(" ");
	ao.setOperatorId("123");
	
	int pageNo = 1 ;
	int pageSize = 5 ;
	
	DocumentLogFunction fun = FunctionFactory.getFunction(DocumentLogFunction.class);
	PaginationQueryResult<DocumentLogEntity> result = fun.queryLog(ao, pageNo, pageSize);
	
	System.out.println(result);
}
}
