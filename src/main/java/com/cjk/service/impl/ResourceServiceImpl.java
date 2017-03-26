package com.cjk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjk.dao.ResourceMapper;
import com.cjk.dto.ResourceNode;
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
	public List<ResourceNode> getResourceTree(){
		List<ResourceNode> nodes = resourceMapper.getAllResource();
		return getCascadeTree(nodes);
	}
	
	protected List<ResourceNode> getCascadeTree(List<ResourceNode> notes){
		List<ResourceNode> topNotes = new ArrayList<>();		//顶级节点
		for(ResourceNode note1: notes){
			long parentId = note1.getParentId();
			boolean hasFound = false;	                    //是否找到父节点
			
			for(ResourceNode note2: notes){
				if(note1 == note2){continue;}
				long noteId = note2.getId();
				if(noteId == parentId){
					hasFound = true;
					List<ResourceNode> childNoteList = note2.getChildren();
					if(null == childNoteList){	            //该节点为对应父节点找到的第一个节点，需先初始化
						childNoteList = new ArrayList<ResourceNode>();
						note2.setChildren(childNoteList);
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
}
