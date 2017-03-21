package com.cjk.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cjk.domain.Role;
import com.cjk.domain.User;
import com.cjk.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		Set<Role> roleSet = new HashSet<>();
		Role role = new Role();
		role.setRoleId(1L);
		role.setRoleName("ROLE_ADMIN");
		roleSet.add(role);
		
		user.setUserId(1L);
		user.setUsername("admin");
		user.setPassword("123");
		user.setRoleSet(roleSet);
		return user;
	}
	
}
