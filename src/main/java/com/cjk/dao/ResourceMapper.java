package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {
	List<Map<String, String>> getPerm2RoleList();
}
