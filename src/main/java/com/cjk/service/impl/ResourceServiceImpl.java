package com.cjk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjk.dao.ResourceMapper;
import com.cjk.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Map<String, String>> getPerm2RoleList() {
		return resourceMapper.getPerm2RoleList();
	}
}
