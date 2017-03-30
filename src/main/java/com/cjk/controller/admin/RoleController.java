package com.cjk.controller.admin;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.dto.admin.RoleDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.entity.JsonResult;
import com.cjk.param.RoleAddParam;
import com.cjk.param.RoleAlterParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.RoleService;
import com.cjk.utils.BeanUtils;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public JsonResult getAllByPage(@Valid PageParam pageParam, BindingResult br) throws Throwable{
		PageDTO<RoleDTO> dto = roleService.getAll(BeanUtils.bean2Map(pageParam));
		return JsonResult.success().setData(dto);
	}
	
	@RequestMapping(value="/getRoleEnum", method=RequestMethod.GET)
	public JsonResult getAll() throws Throwable{
		List<Map<String, Object>> data = roleService.getRoleEnum();
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
