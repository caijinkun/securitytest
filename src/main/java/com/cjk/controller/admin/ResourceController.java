package com.cjk.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.domain.Resource;
import com.cjk.dto.ResourceNodeDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.entity.JsonResult;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;
import com.cjk.param.ResourceQueryParam;
import com.cjk.service.ResourceService;

@RestController
@RequestMapping("/admin/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/getResourceTree")
	public JsonResult getResourceTree() throws Throwable{
		 List<ResourceNodeDTO> resources = resourceService.getResourceTree();
		 return JsonResult.success().setData(resources);
	}
	
	@RequestMapping("/getAll")
	public JsonResult getAll(ResourceQueryParam ResourceQueryParam) throws Throwable{
		PageDTO<Resource> resources = resourceService.getAll(ResourceQueryParam);
		return JsonResult.success().setData(resources);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JsonResult create(ResourceAddParam param) throws Throwable{
		resourceService.create(param);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JsonResult delete(Long userId) throws Throwable{
		return JsonResult.success().setData("未开发");
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JsonResult update(ResourceAlterParam param) throws Throwable{
		resourceService.update(param);
		return JsonResult.success();
	}
}
