package com.cjk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.domain.User;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.common.PageParam;
import com.cjk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public Object getAll(PageParam pageParam) throws Throwable{
		PageDTO<User> dto = userService.getAll(pageParam);
		return dto;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Object update() throws Throwable{
		return new Object();
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	public Object updatePassword() throws Throwable{
		return new Object();
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Object delete() throws Throwable{
		return new Object();
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Object create() throws Throwable{
		return new Object();
	}
}
