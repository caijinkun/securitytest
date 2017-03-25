package com.cjk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjk.dao.RoleMapper;
import com.cjk.dao.RoleResourceLinkMapper;
import com.cjk.domain.Role;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleResourceLinkMapper RoleResourceLinkMapper;
	
	@Override
	public void create(RoleAddParam param) throws Exception {
		
	}

	@Override
	public void update(RoleAlterParam param) throws Exception {
		
	}

	@Override
	public PageDTO<Role> getAll(PageParam pageParam) throws Exception {
		return null;
	}

}
