package com.cjk.service;

import java.util.List;
import java.util.Map;

import com.cjk.dto.admin.RoleDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;

public interface RoleService {
	void create(RoleAddParam param) throws Exception;
	void update(RoleAlterParam param) throws Exception;
	PageDTO<RoleDTO> getAll(Map<String, Object> param) throws Exception;
	List<Map<String, Object>> getRoleEnum() throws Exception;
}
