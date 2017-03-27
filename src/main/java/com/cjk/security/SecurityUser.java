package com.cjk.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cjk.constant.Constant;
import com.cjk.domain.Role;
import com.cjk.domain.User;

public class SecurityUser extends User implements UserDetails{
	private static final long serialVersionUID = 1L;

	public SecurityUser(){}
	public SecurityUser(User user){
		BeanUtils.copyProperties(user, this);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedSet = new HashSet<>();
		Set<Role> roleSet = this.getRoleSet();
		for(Role role : roleSet){
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
			grantedSet.add(grantedAuthority);
		}
		return grantedSet;
	}

	@Override
	public boolean isAccountNonExpired() {
		long now = new Date().getTime();
		return this.getExpireAt() == Constant.USER_NEVER_EXPIRE || this.getExpireAt() >= now;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getLocked() == Constant.USER_NON_LOCKED;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getStatus() != Constant.USER_DISABLED;
	}

}
