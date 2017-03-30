package com.cjk.dto.admin;

import java.util.List;

public class ResourceNodeDTO {
	private Long resourceId;
	private String text;
	private Long parentId;
	private String url;
	private String permision;
	public List<ResourceNodeDTO> nodes;
	
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<ResourceNodeDTO> getNodes() {
		return nodes;
	}
	public void setNodes(List<ResourceNodeDTO> nodes) {
		this.nodes = nodes;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermision() {
		return permision;
	}
	public void setPermision(String permision) {
		this.permision = permision;
	}
}
