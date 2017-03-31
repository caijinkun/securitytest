package com.cjk.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.dto.admin.ResourceNodeDTO;
import com.cjk.entity.JsonResult;
import com.cjk.security.SecurityUser;
import com.cjk.service.ResourceService;
import com.cjk.utils.UserUtils;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/menu")
	public JsonResult menu() throws Throwable{
		SecurityUser user = UserUtils.getLoginUser();
		List<ResourceNodeDTO> dtoList = resourceService.getResourceMenu(user);
		return JsonResult.success().setData(dtoList);
	}
}
