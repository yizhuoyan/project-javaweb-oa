package com.neusoft.oa.attendance.function.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.oa.attendance.dao.ManagerAtteCountDao;
import com.neusoft.oa.attendance.dao.impl.ManagerAtteCountDaoImpl;
import com.neusoft.oa.attendance.dto.PageFindResult;
import com.neusoft.oa.attendance.entity.AtteCountEntity;
import com.neusoft.oa.attendance.entity.AtteRetroactiveEntity;
import com.neusoft.oa.attendance.entity.AtteVacateEntity;
import com.neusoft.oa.attendance.function.DeptManagerFunction;
import com.neusoft.oa.core.dao.DaoFactory;

import static com.neusoft.oa.core.util.ThisSystemUtil.*;
import static com.neusoft.oa.core.util.AssertThrowUtil.*;

public class DeptManagerFunctionImpl implements DeptManagerFunction {

	@Override
	public List<AtteCountEntity> queryCounts(String managerId, int mouth, String key, int pageNo, int pageSize)
			throws Exception {
		// 1验证参数
		// 1.1去掉字符串前后空格
		key = trim(key);
		// 1.2pageNo和PageSize不能为负数和0
		pageNo = assertPositiveInteger(pageNo, 1);
		pageSize = assertPositiveInteger(pageSize, 10);
		// 1.3页面必须在5-100之间
		final int min = 5;
		final int max = 100;
		final String message = String.format("每页大小必须在%d-%d之间", min, max);
		assertBetween(message, pageSize, min, max);

		// 2执行业务逻辑
		ManagerAtteCountDao dao = DaoFactory.getDao(ManagerAtteCountDao.class);
		//分页数据结果
		List<AtteCountEntity> pageData=new ArrayList<>(pageSize);
		// 执行业务方法
		int totalRows = dao.selectsAtteCount(managerId, mouth,key,pageNo,pageSize,pageData);

		// 3组装业务结果
		PageFindResult result=new PageFindResult();
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		result.setRows(pageData);
		result.setTotalRows(totalRows);
		
		return result;
		return rs;
	}

	@Override
	public List<AtteVacateEntity> queryVacate(String managerId, int mouth, String key, String pageNo, String pageSize)
			throws Exception {
				return null;
			
	}

	@Override
	public List<AtteRetroactiveEntity> queryRetroactive(String managerId, String key, String pageNo, String pageSize)
			throws Exception {
				return null;
		
	}

	static public int assertPositiveInteger(int n, int defaultValue) {
		if (n <= 0)
			return defaultValue;
		return n;
	}

	public static void assertBetween(String message, int n, int min, int max) {
		if (n < min || n > max) {
			throw new OAExeception(message);
		}
	}

}
