package com.cjk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/hello")
	public @ResponseBody String hello(){
		return "hello";
	}
	
	@RequestMapping("/hello2")
	public String hello2(){
		return "hello";
	}
}
