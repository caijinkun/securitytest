package com.cjk.param;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UserAlterParam {
	@NotNull
	private Long userId;
	@NotBlank
	private String username;
	@NotNull
	private Integer locked;
	private Set<Long> roleIds;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Set<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
