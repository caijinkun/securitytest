package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.dto.admin.RoleDTO;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;

@Mapper
public interface RoleMapper {
	List<RoleDTO> getAll(Map<String, Object> param);
	int getAllCount(Map<String, Object> param);
	long create(RoleAddParam param);
	int update(RoleAlterParam param);
	List<Map<String, Object>> getRoleEnum();
}
