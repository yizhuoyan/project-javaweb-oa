package com.neusoft.oa.system.entity;

import java.time.Instant;
import java.util.Map;

import com.neusoft.oa.core.KeyValueMap;
import com.neusoft.oa.core.dto.VOMap;

/**
 * 系统功能模块
 * @author yizhuoyan@hotmail.com
 *
 */
public class SysModuleEntity {
	public static final int FLAG_ENABLE=1,FLAG_DISABLE=0;
	private String id;
	private String code;
	private String name;
	private String icon;
	private String url;
	/**
	 * 父模块
	 */
	private SysModuleEntity parentModule;
	
	private String remark;
	
	private String showOrder;
	/**
	 * 0=停用
	 * 1=启用
	 */
	private int flag;
	/**
	 * 创建人，谁创建的谁才能修改
	 */
	private SysUserEntity createUser;
	/**
	 * 创建时间
	 */
	private Instant createTime;
	
	
	

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public SysModuleEntity getParentModule() {
		return parentModule;
	}

	public void setParentModule(SysModuleEntity parentModule) {
		this.parentModule = parentModule;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}
	public VOMap toSimpleVo() {
		VOMap m=VOMap.of(6);
		m.put("id",this.id);
		m.put("code",this.code);
		m.put("name",this.name);
		m.put("url",this.url);
		m.put("remark",this.remark);
		if(this.parentModule!=null) {
			m.put("parent", VOMap.of(2)
					.put("id", this.parentModule.id));
		}
		return m;
	}
	public VOMap toVo() {
		VOMap m=VOMap.of(8);
		m.put("id",this.id);
		m.put("code",this.code);
		m.put("name",this.name);
		m.put("icon",this.icon);
		m.put("url",this.url);
		m.put("remark",this.remark);
		if(this.parentModule!=null) {
			m.put("parent", VOMap.of(2)
					.put("id", this.parentModule.id)
					.put("name", this.parentModule.name));
		}
		if(this.createUser!=null) {
			m.put("createUser",VOMap.of(2)
					.put("id", this.createUser.getId())
					.put("name",this.createUser.getName()));
		}
		m.put("createTime", this.createTime);
		return m;
	}
	@Override
	public String toString() {
		return "SysModuleEntity [id=" + id + ", code=" + code + ", name=" + name + ", icon=" + icon + ", url=" + url
				+ ",  parentModule=" + parentModule + ", remark=" + remark + ", showOrder="
				+ showOrder + "]";
	}

	public SysUserEntity getCreateUser() {
		return createUser;
	}

	public void setCreateUser(SysUserEntity createUser) {
		this.createUser = createUser;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}
	
	

}
