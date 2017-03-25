package com.cjk.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.domain.User;
import com.cjk.dto.common.PageDTO;
import com.cjk.entity.JsonResult;
import com.cjk.entity.Param;
import com.cjk.param.UserAddParam;
import com.cjk.param.UserAlterParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public JsonResult getAll(@Valid PageParam pageParam, BindingResult br) throws Throwable{
		PageDTO<User> dto = userService.getAll(pageParam);
		return JsonResult.success().setData(dto);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JsonResult update(UserAlterParam param) throws Throwable{
		userService.update(param);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	public JsonResult updatePassword(long alterId, String password) throws Throwable{
		Param param = new Param().append("userId", alterId)
						.append("password", password);
		userService.updatePassword(param);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JsonResult delete(Long id) throws Throwable{
		userService.deleteLogic(id);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JsonResult create(UserAddParam param) throws Throwable{
		userService.create(param);
		return JsonResult.success();
	}
}
