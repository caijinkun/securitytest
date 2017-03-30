package com.cjk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/test1").setViewName("test1");
		registry.addViewController("/test2").setViewName("test2");
		registry.addViewController("/test3").setViewName("test3");
		
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/loginPage").setViewName("/loginPage");
		registry.addViewController("/admin/user").setViewName("/admin/userManage");
		registry.addViewController("/admin/role").setViewName("/admin/roleManage");
		registry.addViewController("/admin/resource").setViewName("/admin/resourceManage");
	}
}
