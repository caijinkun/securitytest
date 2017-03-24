package com.cjk.service;

import com.cjk.domain.User;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.common.PageParam;

public interface UserService {
	User getUserByUsername(String username);
	PageDTO<User> getAll(PageParam pageParam);
}
