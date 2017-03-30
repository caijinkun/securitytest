package com.cjk.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjk.dao.RoleMapper;
import com.cjk.dao.RoleResourceLinkMapper;
import com.cjk.domain.RoleResourceLink;
import com.cjk.dto.admin.RoleDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;
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
		roleMapper.create(param);
		Long newId = param.getRoleId();
		Set<Long> resources = param.getResources();
		for(long resource : resources){
			RoleResourceLink roleResourceLink = new RoleResourceLink();
			roleResourceLink.setResourceId(resource);
			roleResourceLink.setRoleId(newId);
			RoleResourceLinkMapper.create(roleResourceLink);
		}
	}

	@Override
	public void update(RoleAlterParam param) throws Exception {
		roleMapper.update(param);
		Long roleId = param.getRoleId();
		RoleResourceLinkMapper.deleteByRoleId(roleId);
		
		Set<Long> resources = param.getResources();
		for(long resource : resources){
			RoleResourceLink roleResourceLink = new RoleResourceLink();
			roleResourceLink.setResourceId(resource);
			roleResourceLink.setRoleId(roleId);
			RoleResourceLinkMapper.create(roleResourceLink);
		}
	}

	@Override
	public PageDTO<RoleDTO> getAll(Map<String, Object> param) throws Exception {
		PageDTO<RoleDTO> dto = new PageDTO<>();
		List<RoleDTO> roleList = roleMapper.getAll(param);
		int total = roleMapper.getAllCount(param);
		dto.setTotal(total);
		dto.setRows(roleList);
		return dto;
	}
	@Override
	public List<Map<String, Object>> getRoleEnum() throws Exception {
		return roleMapper.getRoleEnum();
	}

}
