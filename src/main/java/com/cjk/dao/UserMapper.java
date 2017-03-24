package com.cjk.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.User;

@Mapper
public interface UserMapper {
//	User getById(Integer userId);
	User getByUsername(String username);
}
