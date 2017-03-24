package com.cjk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjk.dao.UserMapper;
import com.cjk.domain.User;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.common.PageParam;
import com.cjk.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	@Override
	public PageDTO<User> getAll(PageParam pageParam) {
		PageDTO<User> dto = new PageDTO();
		List<User> userList = userMapper.getAll(pageParam);
		int total = userMapper.getAllCount();
		dto.setRows(userList);
		dto.setTotal(total);
		return dto;
	}
	
}
