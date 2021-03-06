package com.cjk.param;

import java.util.Set;

public class RoleAlterParam {
	private Long roleId;
	private String roleName;
	private String description;
	private Set<Long> resources;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Long> getResources() {
		return resources;
	}
	public void setResources(Set<Long> resources) {
		this.resources = resources;
	}
}
