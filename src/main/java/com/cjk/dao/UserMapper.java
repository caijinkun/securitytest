package com.cjk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.User;
import com.cjk.param.common.PageParam;

@Mapper
public interface UserMapper {
//	User getById(Integer userId);
	User getByUsername(String username);
	List<User> getAll(PageParam pageParam);
	int getAllCount();
}
