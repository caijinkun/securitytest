package com.cjk.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.domain.Role;
import com.cjk.dto.common.PageDTO;
import com.cjk.entity.JsonResult;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.RoleService;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/getAllByPage", method=RequestMethod.GET)
	public JsonResult getAllByPage(@Valid PageParam pageParam, BindingResult br) throws Throwable{
		PageDTO<Role> dto = roleService.getAllByPage(pageParam);
		return JsonResult.success().setData(dto);
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public JsonResult getAll() throws Throwable{
		List<Role> data = roleService.getAll();
		return JsonResult.success().setData(data);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JsonResult create(RoleAddParam param) throws Throwable{
		roleService.create(param);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JsonResult update(RoleAlterParam param) throws Throwable{
		roleService.update(param);
		return JsonResult.success();
	}
}
