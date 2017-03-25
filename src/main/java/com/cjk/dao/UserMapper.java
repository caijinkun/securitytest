package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.User;
import com.cjk.param.UserAddParam;
import com.cjk.param.UserAlterParam;
import com.cjk.param.common.PageParam;

@Mapper
public interface UserMapper {
	User getByUsername(String username);
	List<User> getAll(PageParam pageParam);
	int getAllCount();
	int update(UserAlterParam param);
	void updatePassword(Map<String, Object> param);
	int create(UserAddParam param);
	int deleteLogic(Long delId);
}
