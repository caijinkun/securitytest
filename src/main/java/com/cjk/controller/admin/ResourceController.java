package com.cjk.controller.admin;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjk.domain.Resource;
import com.cjk.dto.admin.ResourceNodeDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.entity.JsonResult;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;
import com.cjk.param.ResourceQueryParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.ResourceService;
import com.cjk.utils.BeanUtils;

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
	public JsonResult getAll(PageParam pageParam, ResourceQueryParam resourceQueryParam) throws Throwable{
		Map<String, Object> param = BeanUtils.bean2Map(pageParam, resourceQueryParam);
		PageDTO<Resource> resources = resourceService.getAll(param);
		return JsonResult.success().setData(resources);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JsonResult create(@Valid ResourceAddParam param, BindingResult br) throws Throwable{
		resourceService.create(param);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JsonResult delete(Long userId) throws Throwable{
		return JsonResult.success();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JsonResult update(@Valid ResourceAlterParam param, BindingResult br) throws Throwable{
		resourceService.update(param);
		return JsonResult.success();
	}
}
