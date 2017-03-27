package com.cjk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mytest")
public class TestController {
	@RequestMapping("/test1")
	public @ResponseBody Object test1(){
		return "hello";
	}
}
