package com.cjk.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjk.dao.UserMapper;
import com.cjk.dao.UserRoleLinkMapper;
import com.cjk.domain.User;
import com.cjk.domain.UserRoleLink;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.UserAddParam;
import com.cjk.param.UserAlterParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleLinkMapper userRoleLinkMapper;
	
	@Override
	public User getUserByUsername(String username) throws Exception {
		return userMapper.getByUsername(username);
	}

	@Override
	public PageDTO<User> getAll(PageParam pageParam) throws Exception {
		PageDTO<User> dto = new PageDTO<>();
		List<User> userList = userMapper.getAll(pageParam);
		int total = userMapper.getAllCount();
		dto.setRows(userList);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public void update(UserAlterParam param) throws Exception{
		userMapper.update(param);
		long userId = param.getUserId();
		
		userRoleLinkMapper.deleteByUserId(userId);
		
		Set<Long> roleIds = param.getRoleIds();
		for(long roleId : roleIds){
			UserRoleLink linkParam = new UserRoleLink();
			linkParam.setRoleId(roleId);
			linkParam.setUserId(userId);
			userRoleLinkMapper.create(linkParam);
		}
	}

	@Override
	public void updatePassword(Map<String, Object> param)  throws Exception{
		userMapper.updatePassword(param);
	}

	@Override
	public void create(UserAddParam param) {
		userMapper.create(param);
		long createId = param.getUserId();
		Set<Long> roleIds = param.getRoleIds();
		for(long roleId : roleIds){
			UserRoleLink linkParam = new UserRoleLink();
			linkParam.setRoleId(roleId);
			linkParam.setUserId(createId);
			userRoleLinkMapper.create(linkParam);
		}
	}

	@Override
	public void deleteLogic(Long delId) throws Exception {
		userMapper.deleteLogic(delId);
	}
}
