package com.cjk.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.RoleResourceLink;

@Mapper
public interface RoleResourceLinkMapper {
	void create(RoleResourceLink param);
	void deleteByRoleId(Long roleId);
}
