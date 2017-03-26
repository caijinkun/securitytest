package com.cjk.dto;

import java.util.List;

public class ResourceNode {
	public Long id;
	public String text;
	public String state;
	public Long parentId;
	public List<ResourceNode> children;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<ResourceNode> getChildren() {
		return children;
	}
	public void setChildren(List<ResourceNode> children) {
		this.children = children;
	}
}
