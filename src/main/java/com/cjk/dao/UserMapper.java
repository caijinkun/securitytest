package com.cjk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cjk.domain.User;
import com.cjk.dto.admin.UserDTO;
import com.cjk.param.UserAddParam;
import com.cjk.param.UserAlterParam;

@Mapper
public interface UserMapper {
	User getByUsername(String username);
	List<UserDTO> getAll(Map<String, Object> param);
	int getAllCount(Map<String, Object> param);
	int update(UserAlterParam param);
	void updatePassword(Map<String, Object> param);
	int create(UserAddParam param);
	int deleteLogic(Long delId);
}
