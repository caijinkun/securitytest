package com.cjk.service;

import com.cjk.domain.Role;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;
import com.cjk.param.common.PageParam;

public interface RoleService {
	void create(RoleAddParam param) throws Exception;
	void update(RoleAlterParam param) throws Exception;
	PageDTO<Role> getAll(PageParam pageParam) throws Exception;
}
