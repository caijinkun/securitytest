package com.cjk.service;

import java.util.List;
import java.util.Map;

import com.cjk.domain.User;
import com.cjk.dto.admin.ResourceDTO;
import com.cjk.dto.admin.ResourceNodeDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;

public interface ResourceService {
	List<Map<String, String>> getPerm2RoleList();
	List<ResourceNodeDTO> getResourceTree();
	void create(ResourceAddParam param) throws Exception;
	PageDTO<ResourceDTO> getAll(Map<String, Object> pageParam) throws Exception;
	void update(ResourceAlterParam param) throws Exception;
	List<ResourceNodeDTO> getResourceMenu(User user);
}
