package com.cjk.domain;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String username;
	private String password;
	private Integer status;
	private Integer locked;
	private Set<Role> roleSet;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Set<Role> getRoleSet() {
		return roleSet;
	}
	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
	
}
