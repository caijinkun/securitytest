package com.cjk.dto.admin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceNodeDTO {
	private Long resourceId;
	private String text;
	private Integer type;
	private Integer resourceOrder;
	@JsonProperty("href")
	private String url;
	private Long parentId;
	private String permision;
	public List<ResourceNodeDTO> nodes;
	private String icon;
	private Integer nodeType;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getResourceOrder() {
		return resourceOrder;
	}
	public void setResourceOrder(Integer resourceOrder) {
		this.resourceOrder = resourceOrder;
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
	public String getPermision() {
		return permision;
	}
	public void setPermision(String permision) {
		this.permision = permision;
	}
	public List<ResourceNodeDTO> getNodes() {
		return nodes;
	}
	public void setNodes(List<ResourceNodeDTO> nodes) {
		this.nodes = nodes;
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
