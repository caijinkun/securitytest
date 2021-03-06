package com.cjk.domain;

public class Resource {
	private Long resourceId;
	private String resourceName;
	private Integer type;
	private Integer resourceOrder;
	private String url;
	private Long parentId;
	private String parentIds;
	private String permision;
	private String remark;
	private String icon;
	private Integer nodeType;
	
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getPermision() {
		return permision;
	}
	public void setPermision(String permision) {
		this.permision = permision;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getResourceOrder() {
		return resourceOrder;
	}
	public void setResourceOrder(Integer resourceOrder) {
		this.resourceOrder = resourceOrder;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getNodeType() {
		return nodeType;
	}
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
}
