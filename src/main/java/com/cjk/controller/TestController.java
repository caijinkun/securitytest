package com.cjk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjk.dao.UserMapper;
import com.cjk.param.UserAddParam;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/hello")
	public @ResponseBody Object hello(UserAddParam param){
//		System.out.println(param);
//		Long res = userMapper.create(param);
		return null;
	}
}
