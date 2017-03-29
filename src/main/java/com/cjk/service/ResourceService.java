package com.cjk.service;

import java.util.List;
import java.util.Map;

import com.cjk.domain.Resource;
import com.cjk.dto.ResourceNodeDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;
import com.cjk.param.common.PageParam;

public interface ResourceService {
	List<Map<String, String>> getPerm2RoleList();
	List<ResourceNodeDTO> getResourceTree();
	void create(ResourceAddParam param) throws Exception;
	PageDTO<Resource> getAll(PageParam pageParam) throws Exception;
	void update(ResourceAlterParam param) throws Exception;
}
