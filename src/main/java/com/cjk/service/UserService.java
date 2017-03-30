package com.cjk.service;

import java.util.Map;

import com.cjk.domain.User;
import com.cjk.dto.admin.UserDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.UserAddParam;
import com.cjk.param.UserAlterParam;

public interface UserService {
	User getUserByUsername(String username) throws Exception;
	PageDTO<UserDTO> getAll(Map<String, Object> param) throws Exception;
	void update(UserAlterParam param) throws Exception;
	void updatePassword(Map<String, Object> param) throws Exception;
	void create(UserAddParam param) throws Exception;
	void deleteLogic(Long delId) throws Exception;
}
