package com.cjk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjk.dao.UserMapper;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/hello")
	public @ResponseBody Object hello(){
		return userMapper.getByUsername("admin");
	}
	
	@RequestMapping("/hello2")
	public String hello2(){
		return "hello";
	}
}
