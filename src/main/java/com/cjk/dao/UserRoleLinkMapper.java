package com.cjk.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.UserRoleLink;

@Mapper
public interface UserRoleLinkMapper {
	int create(UserRoleLink param);
	int deleteByUserId(Long userId);
}
