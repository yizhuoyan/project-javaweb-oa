package com.neusoft.oa.system.function.impl;

import static com.neusoft.oa.core.util.AssertThrowUtil.$;
import static com.neusoft.oa.core.util.AssertThrowUtil.assertLessThan;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.neusoft.oa.core.CurrentThreadUtil;
import com.neusoft.oa.core.OAException;
import com.neusoft.oa.core.dao.DBUtil;
import com.neusoft.oa.core.dao.DaoFactory;
import com.neusoft.oa.core.dto.PaginationQueryResult;
import com.neusoft.oa.core.dto.QueryResult;
import com.neusoft.oa.core.util.ThisSystemUtil;
import com.neusoft.oa.system.ao.SysModuleAo;
import com.neusoft.oa.system.ao.SysRoleAo;
import com.neusoft.oa.system.ao.SysUserAo;
import com.neusoft.oa.system.dao.SysModuleDao;
import com.neusoft.oa.system.dao.SysRoleDao;
import com.neusoft.oa.system.dao.SysUserDao;
import com.neusoft.oa.system.entity.SysModuleEntity;
import com.neusoft.oa.system.entity.SysRoleEntity;
import com.neusoft.oa.system.entity.SysUserEntity;
import com.neusoft.oa.system.function.AdministratorFunction;

public class AdministratorFunctionImpl  implements AdministratorFunction {
	SysModuleDao mdao = DaoFactory.getDao(SysModuleDao.class);
	SysRoleDao rdao=DaoFactory.getDao(SysRoleDao.class);
	SysUserDao udao=DaoFactory.getDao(SysUserDao.class);
	
	@Override
	public void lockUser(String id) throws Exception {
		id=$("id",id);
		udao.update(id, "flag",SysUserEntity.FLAG_LOCK);
	}
	@Override
	public void unlockUser(String id) throws Exception {
		id=$("id",id);
		udao.update(id, "flag",SysUserEntity.FLAG_NORMAL);
	}
	@Override
	public SysModuleEntity addSysModule(SysModuleAo ao) throws Exception {
		// 1 清理验证参数
		String code = $("代号", ao.getCode());
		String name = $("名字", ao.getName());
		String createUserId=$("创建人Id",ao.getCreateUserId());
		String icon = ThisSystemUtil.trim(ao.getIcon());
		String url = ThisSystemUtil.trim(ao.getUrl());
		String parentId = ThisSystemUtil.trim(ao.getParentId());
		String showOrder = ThisSystemUtil.trim(ao.getShowOrder());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		// 2 执行业务逻辑
		// 2.1代号不能重复
		if (mdao.exist("code", code)) {
			OAException.throwWithMessage("代号【?】已存在", code);
		}
		// 2.2验证父模块是否存在
		SysModuleEntity parentModule=null;
		if (parentId != null) {
			if (!mdao.exist("id", parentId)) {
				OAException.throwWithMessage("父模块【?】不存在", parentId);
			}
			parentModule=new SysModuleEntity();
			parentModule.setId(parentId);
		}
		//2.3创建人
		if(!udao.exist("id", createUserId)) {
			OAException.throwWithMessage("创建人【?】不存在", createUserId);
		}
		SysUserEntity createUser=new SysUserEntity();
		createUser.setId(createUserId);
		
		// 2.4 存入数据库
		SysModuleEntity t = new SysModuleEntity();
		t.setId(DBUtil.uuid());
		t.setCode(code);
		t.setIcon(icon);
		t.setName(name);
		t.setParentModule(parentModule);
		t.setRemark(remark);
		t.setShowOrder(showOrder);
		t.setUrl(url);
		t.setFlag(SysModuleEntity.FLAG_ENABLE);
		t.setCreateUser(createUser);
		t.setCreateTime(Instant.now());
		mdao.insert(t);
		return t;
	}



	@Override
	public List<SysModuleEntity> loadAllModules() throws Exception {
		List<SysModuleEntity> modules = mdao.selectAll("code");
		return modules;
	}

	@Override
	public List<SysModuleEntity> loadRootModules() throws Exception {
		List<SysModuleEntity> modules = mdao.selectAllByColumn("parent_id",null,"code");
		return modules;
	}
	@Override
	public void modifySysModule(String id, SysModuleAo ao) throws Exception {
		// 1清理验证参数
		id = $("id", id);
		String code = $("代号", ao.getCode());
		String parentId=ThisSystemUtil.trim(ao.getParentId());
		String name = $("名字", ao.getName());
		String icon = ThisSystemUtil.trim(ao.getIcon());
		String url = ThisSystemUtil.trim(ao.getUrl());
		String showOrder = ThisSystemUtil.trim(ao.getShowOrder());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		// 2执行业务逻辑
		// 2.1 查询出旧数据
		SysModuleEntity old = mdao.select("id", id);
		// 2.2 验证数据id是否有效
		if (old == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		
		String currentUserId=CurrentThreadUtil.getCurrentUserId();
		//判断是否是创建人
		if(!old.getCreateUser().getId().equals(currentUserId)) {
			throw new OAException("您无权限进行此操作");
		}
		
		Map<String, Object> needUpdate = new HashMap<>(8, 1);
		//2.3父模块
		SysModuleEntity parentModule=old.getParentModule();
		String oldParentId=parentModule==null?null:parentModule.getId();
		
		if(!Objects.equals(parentId, oldParentId)) {
			//父模块不能是本身
			if(Objects.equals(parentId, id)) {
				OAException.throwWithMessage("父模块不能是本身");
			}
			//父模块必须存在
			if(parentId!=null) {
				if(!mdao.exist(id, parentId)) {
					OAException.throwWithMessage("父模块不存在");
				}
			}
			needUpdate.put("parent_id", parentId);
		}
		// 2.4 代号
		if (!code.equals(old.getCode())) {
			// 说明修改代号
			// 2.2.1 新代号不能存在
			if (mdao.exist("code", code)) {
				OAException.throwWithMessage("代号【?】已存在", code);
			}
			needUpdate.put("code", code);
		}
		// 2.3 名字
		if (!name.equals(old.getName())) {
			assertLessThan("名字不能超过?个字符",name.length(), 32);
			needUpdate.put("name", name);
		}
		//icon
		if(!Objects.equals(icon, old.getIcon())) {
			needUpdate.put("icon", icon);	
		}
		//url
		if(!Objects.equals(url, old.getUrl())) {
			
			needUpdate.put("url", url);
		}
		//remark
		if(!Objects.equals(remark, old.getRemark())) {
			needUpdate.put("remark", remark);
			
		}
		// 2.4 其他
		if(!Objects.equals(showOrder, old.getShowOrder())) {
			needUpdate.put("showorder", showOrder);
		}
		// 2.5 进行数据库更新
		mdao.update(id, needUpdate);
	}

	@Override
	public SysModuleEntity loadSysModule(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑
		SysModuleEntity e = mdao.select("id", id);
		// 2.1 验证id
		if (e == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		// 2.2 查询出父模块
		SysModuleEntity parentModule=e.getParentModule();
		if (parentModule!= null) {
			parentModule = mdao.select("id", parentModule.getId());
			e.setParentModule(parentModule);
		}
		//2.3 查询出创建人
		SysUserEntity createUser=e.getCreateUser();
		if(createUser!=null) {
			createUser=udao.select("id",createUser.getId());
			e.setCreateUser(createUser);
		}
		// 3组装业务结果
		return e;
	}

	@Override
	public void deleteSysmodule(String id) throws Exception {
		id = $("id", id);
		SysModuleEntity module=mdao.select("id",id);
		if(module==null) {
			return;
		}
		
		String currentUserId=CurrentThreadUtil.getCurrentUserId();
		//判断是否是创建人
		if(!module.getCreateUser().getId().equals(currentUserId)) {
			throw new OAException("您无权限进行此操作");
		}
		// 包含子模块不允许删除
		if (mdao.exist("parent_id", id)) {
			throw new OAException("此功能模块包含子功能不允许删除");
		}
		
		// 删除关联关系
		mdao.unjoinRole(id);
		//删除
		mdao.delete("id",id);
	}

	@Override
	public SysRoleEntity addSysRole(SysRoleAo ao) throws Exception {
		// 1 清理验证参数
		String code = $("代号", ao.getCode());
		String name = $("名字", ao.getName());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		// 2 执行业务逻辑
		// 2.1代号不能重复
		if (rdao.exist("code", code)) {
			OAException.throwWithMessage("代号【?】已存在", code);
		}
		// 2.3 存入数据库
		SysRoleEntity t = new SysRoleEntity();
		t.setId(DBUtil.uuid());
		t.setCode(code);
		t.setName(name);
		t.setRemark(remark);
		rdao.insert(t);
		return t;
	}

	@Override
	public QueryResult<SysRoleEntity> querySysRoleByKey(String key) throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		// 2执行业务逻辑
		List<SysRoleEntity> list = rdao.selectsByKey(key);
		// 3组装业务结果
		QueryResult<SysRoleEntity> result = new QueryResult<>();
		result.setTotalRows(list.size());
		result.setRows(list);
		return result;
	}

	@Override
	public void modifySysRole(String id, SysRoleAo ao) throws Exception {
		// 1清理验证参数
		id = $("id", id);
		String code = $("代号", ao.getCode());
		String name = $("名字", ao.getName());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		// 2执行业务逻辑
		// 2.1 查询出旧数据
		SysRoleEntity old = rdao.select("id", id);
		// 2.2 验证数据id是否有效
		if (old == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		String currentUserId=CurrentThreadUtil.getCurrentUserId();
		//判断是否是创建人
		if(!old.getCreateUser().getId().equals(currentUserId)) {
			throw new OAException("您无权限进行此操作");
		}
		Map<String, Object> needUpdate = new HashMap<>(8, 1);
		// 2.2 代号
		if (!code.equals(old.getCode())) {
			// 说明修改代号
			// 2.2.1 新代号不能存在
			if (rdao.exist("code", code)) {
				OAException.throwWithMessage("代号【?】已存在", code);
			}
			needUpdate.put("code", code);
		}
		// 2.3 名字
		if (!name.equals(old.getName())) {
			if (name.length() > 32) {
				throw new OAException("名字不能超过32个字符");
			}
			needUpdate.put("name", name);
		}

		// 2.4 其他
		needUpdate.put("remark", remark);

		// 2.5 进行数据库更新
		rdao.update(id, needUpdate);
	}

	@Override
	public SysRoleEntity loadSysRole(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑
		SysRoleEntity e = rdao.select("id", id);
		// 2.1 验证id
		if (e == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		//2.2 创建人
		SysUserEntity createUser=e.getCreateUser();
		if(createUser!=null) {
			createUser=udao.select("id",createUser.getId());
			e.setCreateUser(createUser);
		}
		// 3组装业务结果
		return e;
	}

	@Override
	public List<SysRoleEntity> loadAllRole() throws Exception {
		return rdao.selectAll();
	}

	@Override
	public List<SysModuleEntity> loadModulesOfRole(String roleId) throws Exception {
		roleId = $("roleId", roleId);
		List<SysModuleEntity> result = mdao.selectsByRoleId(roleId);
		return result;
	}

	@Override
	public void grantModules2Role(String roleId, String... moduelIds) throws Exception {
		roleId = $("roleId", roleId);
		if (moduelIds.length > 0) {
			//估算大概需要两倍长度，因为里面还有放入对应的父模块id
			Set<String> moduleIdSet = new HashSet<>(moduelIds.length * 2);
			
			// 处理层级关系
			for (String mid : moduelIds) {
				SysModuleEntity module = mdao.select("id", mid);
				//对应模块不存在，忽略
				if(module==null)continue;
				
				// 判断其父节点是否存在数组中
				SysModuleEntity  parentModule=module.getParentModule();
				//parent等于null为顶层模块，不处理
				if (parentModule != null) {
					if(!moduleIdSet.contains(parentModule.getId())) {
						//不存在，则放入
						moduleIdSet.add(parentModule.getId());	
					}
				}
				//放入当前模块id
				moduleIdSet.add(mid);
			}
			// 先清除全部
			rdao.unjoinModules(roleId);
			if(moduelIds.length>0) {
				// 再添加
				rdao.joinModules(roleId, moduleIdSet.toArray(new String[moduelIds.length]));
			}
		}
	}

	@Override
	public void grantRoles2User(String userId, String... roles) throws Exception {
		userId = $("userId", userId);
		// 先清除全部
		udao.unjoinRoles(userId);
		if (roles.length > 0) {
			// 再添加
			udao.joinRoles(userId, roles);
		}
	}

	@Override
	public void deleteSysRole(String id) throws Exception {
		// 1验证清理参数
		id = $("id", id);
		SysRoleEntity role=rdao.select("id", id);
		if(role==null) {
			return ;
		}
		String currentUserId=CurrentThreadUtil.getCurrentUserId();
		//判断是否是创建人
		if(!role.getCreateUser().getId().equals(currentUserId)) {
			throw new OAException("您无权限进行此操作");
		}
		// 2执行业务逻辑
		rdao.delete("id", id);
	}

	@Override
	public SysUserEntity addSysUser(SysUserAo ao) throws Exception {
		// 1 清理验证参数
		String account = $("账号", ao.getAccount());
		String name = $("名字", ao.getName());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		// 2 执行业务逻辑
		// 2.1代号不能重复
		if (udao.exist("account", account)) {
			OAException.throwWithMessage("代号【?】已存在", account);
		}
		// 2.2存入数据库
		SysUserEntity t = new SysUserEntity();
		t.setId(DBUtil.uuid());
		t.setAccount(account);

		t.setAvatar(null);
		t.setCreateTime(new Date());
		t.setLastLoginIP(null);
		t.setLastLoginTime(null);
		t.setLastModPasswordTime(null);
		String defaultPassword = System.getProperty("sys.default-user-password", "123456");
		t.setPassword(ThisSystemUtil.md5(defaultPassword));
		t.setPhone(null);
		t.setSecurityEmail(null);
		t.setName(name);
		t.setRemark(remark);
		t.setFlag(SysUserEntity.FLAG_NORMAL);
		SysUserEntity createUser=new SysUserEntity();
		createUser.setId(CurrentThreadUtil.getCurrentUserId());
		t.setCreateUser(createUser);
		udao.insert(t);
		return t;
	}

	@Override
	public PaginationQueryResult<SysUserEntity> querySysUserByKey(String key, String pageNo, String pageSize)
			throws Exception {
		// 1清理验证参数
		key = ThisSystemUtil.trim(key);
		int pageNoInt =ThisSystemUtil.parseInt(pageNo, 1);
		int pageSizeInt = ThisSystemUtil.parseInt(pageSize, ThisSystemUtil.parseInt(System.getProperty("sys.default-page-size"), 10));
		List<SysUserEntity> pageData = new ArrayList<>(pageSizeInt);
		// 2执行业务逻辑
		int total = udao.selectsByKey(key, pageNoInt, pageSizeInt, pageData);
		// 3组装业务结果
		PaginationQueryResult<SysUserEntity> result = new PaginationQueryResult<>();
		result.setTotalRows(total);
		result.setRows(pageData);
		result.setPageNo(pageNoInt);
		result.setPageSize(pageSizeInt);
		return result;
	}

	@Override
	public List<SysRoleEntity> loadRolesOfUser(String userId) throws Exception {
		userId = $("userId", userId);
		return rdao.selectsByUser(userId);
	}

	@Override
	public void modifySysUser(String id, SysUserAo ao) throws Exception {
		// 1清理验证参数
		id = $("id", id);
		String account = $("账号", ao.getAccount());
		String name = $("名字", ao.getName());
		String remark = ThisSystemUtil.trim(ao.getRemark());
		String flag = ThisSystemUtil.trim(ao.getFlag());
		// 2执行业务逻辑
		// 2.1 查询出旧数据
		SysUserEntity old = udao.select("id", id);
		// 2.2 验证数据id是否有效
		if (old == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		Map<String, Object> needUpdate = new HashMap<>(8, 1);
		// 2.2 代号
		if (!account.equals(old.getAccount())) {
			// 说明修改代号
			// 2.2.1 新代号不能存在
			if (udao.exist("account", account)) {
				OAException.throwWithMessage("代号【?】已存在", account);
			}
			needUpdate.put("account", account);
		}
		// 2.3 名字
		if (!name.equals(old.getName())) {
			if (name.length() > 32) {
				throw new OAException("名字不能超过32个字符");
			}
			needUpdate.put("name", name);
		}
		if (flag != null) {
			int flagInt = Integer.parseInt(flag);
			needUpdate.put("flag", flagInt);
		}
		// 2.4 其他
		needUpdate.put("remark", remark);

		// 2.5 进行数据库更新
		udao.update(id, needUpdate);
	}

	@Override
	public SysUserEntity loadSysUser(String id) throws Exception {
		// 1验证参数
		id = $("id", id);
		// 2执行业务逻辑
		SysUserEntity e = udao.select("id", id);
		// 2.1 验证id
		if (e == null) {
			OAException.throwWithMessage("数据【?】不存在或已删除", id);
		}
		// 3组装业务结果
		//创建人
		SysUserEntity createUser=e.getCreateUser();
		if(createUser!=null) {
			createUser=udao.select("id", createUser.getId());
			e.setCreateUser(createUser);
		}
		return e;
	}

	@Override
	public void deleteSysUser(String id) throws Exception {
		// 1验证清理参数
		id = $("id", id);
		// 2执行业务逻辑
		udao.update(id, "flag", SysUserEntity.FLAG_DELETED);
	}
}
