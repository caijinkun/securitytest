package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.dto.admin.ResourceDTO;
import com.cjk.dto.admin.ResourceNodeDTO;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;

@Mapper
public interface ResourceMapper {
	List<Map<String, String>> getPerm2RoleList();
	List<ResourceNodeDTO> getAllNodeDTO();
	List<ResourceDTO> getAll(Map<String, Object> pageParam);
	int getAllCount(Map<String, Object> param);
	int create(ResourceAddParam param);
	int update(ResourceAlterParam param);
}
