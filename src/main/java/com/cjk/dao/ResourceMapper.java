package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.dto.ResourceNode;

@Mapper
public interface ResourceMapper {
	List<Map<String, String>> getPerm2RoleList();
	List<ResourceNode> getAllResource();
}
