package com.neusoft.oa.base.entity;

import java.util.Map;

import com.neusoft.oa.core.KeyValueMap;

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
	 * 父模块的id
	 */
	private String parentId;
	/**
	 * 父模块
	 */
	private SysModuleEntity parentModule;
	
	private String remark;
	
	private String showorder;
	/**
	 * 0=停用
	 * 1=启用
	 * 9=已删除
	 */
	private int flag;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getShoworder() {
		return showorder;
	}

	public void setShoworder(String showorder) {
		this.showorder = showorder;
	}
	public Map toJson() {
		KeyValueMap m=KeyValueMap.of(5);
		m.put("id",this.id);
		m.put("code",this.code);
		m.put("name",this.name);
		m.put("icon",this.icon);
		m.put("url",this.url);
		m.put("remark",this.remark);
		m.put("showorder",this.showorder);
		if(this.parentModule!=null) {
			m.put("parent", KeyValueMap.of(2)
					.put("id", this.parentModule.id)
					.put("name", this.parentModule.name));
		}
		return m;
	}
	@Override
	public String toString() {
		return "SysModuleEntity [id=" + id + ", code=" + code + ", name=" + name + ", icon=" + icon + ", url=" + url
				+ ", parentId=" + parentId + ", parentModule=" + parentModule + ", remark=" + remark + ", showorder="
				+ showorder + "]";
	}
	
	

}
