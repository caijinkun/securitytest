package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.Resource;
import com.cjk.dto.ResourceNodeDTO;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;
import com.cjk.param.common.PageParam;

@Mapper
public interface ResourceMapper {
	List<Map<String, String>> getPerm2RoleList();
	List<ResourceNodeDTO> getAllResource();
	List<Resource> getAll(PageParam pageParam);
	int getAllCount();
	int create(ResourceAddParam param);
	int update(ResourceAlterParam param);
}
