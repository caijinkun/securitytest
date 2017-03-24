package com.cjk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjk.dao.UserMapper;
import com.cjk.domain.User;
import com.cjk.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		return userMapper.getByUsername(username);
	}
	
}
