package com.cjk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjk.dao.ResourceMapper;
import com.cjk.domain.Resource;
import com.cjk.dto.ResourceNodeDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;
import com.cjk.param.common.PageParam;
import com.cjk.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Map<String, String>> getPerm2RoleList() {
		return resourceMapper.getPerm2RoleList();
	}
	
	@Override
	public List<ResourceNodeDTO> getResourceTree(){
		List<ResourceNodeDTO> nodes = resourceMapper.getAllResource();
		return getCascadeTree(nodes);
	}
	
	protected List<ResourceNodeDTO> getCascadeTree(List<ResourceNodeDTO> notes){
		List<ResourceNodeDTO> topNotes = new ArrayList<>();		//顶级节点
		for(ResourceNodeDTO note1: notes){
			long parentId = note1.getParentId();
			boolean hasFound = false;	                    //是否找到父节点
			
			for(ResourceNodeDTO note2: notes){
				if(note1 == note2){continue;}
				long noteId = note2.getResourceId();
				if(noteId == parentId){
					hasFound = true;
					List<ResourceNodeDTO> childNoteList = note2.getNodes();
					if(null == childNoteList){	            //该节点为对应父节点找到的第一个节点，需先初始化
						childNoteList = new ArrayList<ResourceNodeDTO>();
						note2.setNodes(childNoteList);
					}
					childNoteList.add(note1);
					break;	                                //每个节点只可能有一个父节点
				}
			}
			//找不到父节点则为顶级节点
			if(!hasFound){
				topNotes.add(note1);
			}
		}
		return topNotes;
	}

	@Override
	public void create(ResourceAddParam param) throws Exception {
		resourceMapper.create(param);
	}

	@Override
	public PageDTO<Resource> getAll(PageParam pageParam) throws Exception {
		PageDTO<Resource> dto = new PageDTO<>();
		List<Resource> ResourceList = resourceMapper.getAll(pageParam);
		int total = resourceMapper.getAllCount();
		dto.setRows(ResourceList);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public void update(ResourceAlterParam param) throws Exception {
		resourceMapper.update(param);
	}
}
