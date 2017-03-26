package com.cjk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.Role;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;
import com.cjk.param.common.PageParam;

@Mapper
public interface RoleMapper {
	List<Role> getAllByPage(PageParam pageParam);
	int getAllCount();
	long create(RoleAddParam param);
	int update(RoleAlterParam param);
}
