package com.cjk.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cjk.domain.Role;
import com.cjk.domain.User;

public class SecurityUser extends User implements UserDetails{
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
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
