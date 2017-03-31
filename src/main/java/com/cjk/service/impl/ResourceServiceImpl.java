package com.cjk.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.functors.ConstantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import com.cjk.constant.Constant;
import com.cjk.dao.ResourceMapper;
import com.cjk.domain.Resource;
import com.cjk.domain.User;
import com.cjk.dto.admin.ResourceDTO;
import com.cjk.dto.admin.ResourceNodeDTO;
import com.cjk.dto.common.PageDTO;
import com.cjk.param.ResourceAddParam;
import com.cjk.param.ResourceAlterParam;
import com.cjk.service.ResourceService;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Map<String, String>> getPerm2RoleList() {
		return resourceMapper.getPerm2RoleList();
	}
	
	public List<ResourceNodeDTO> getResourceMenu(User user){
		Set<String> permisionSet = user.getPermisionSet();
		List<ResourceNodeDTO> targetNodes = new ArrayList<>();
		AntPathMatcher matcher = new AntPathMatcher();
		List<ResourceNodeDTO> nodes = resourceMapper.getAllNodeDTO();
		
		for(String perm : permisionSet){
			Iterator<ResourceNodeDTO> nodesIt = nodes.iterator();
			while(nodesIt.hasNext()){
				ResourceNodeDTO nodeDTO = nodesIt.next();
				if (nodeDTO.getType() == Constant.RESOURCE_TYPE_BUTTON || (null != nodeDTO.getNodeType()
						&& nodeDTO.getNodeType() == Constant.RESOURCE_NODE_TYPE_ROOT)) {
					nodesIt.remove();				//移除按钮和根节点
				}else if (matcher.match(perm, nodeDTO.getPermision())) {
					targetNodes.add(nodeDTO);
					nodesIt.remove();               //移除已匹配项
				}
			}
		}
		return getCascadeTree(targetNodes);
	}
	
	@Override
	public List<ResourceNodeDTO> getResourceTree(){
		List<ResourceNodeDTO> nodes = resourceMapper.getAllNodeDTO();
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
	public PageDTO<ResourceDTO> getAll(Map<String, Object> param) throws Exception {
		PageDTO<ResourceDTO> dto = new PageDTO<>();
		List<ResourceDTO> ResourceList = resourceMapper.getAll(param);
		int total = resourceMapper.getAllCount(param);
		dto.setRows(ResourceList);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public void update(ResourceAlterParam param) throws Exception {
		resourceMapper.update(param);
	}
	
	public static void main(String[] args) {
		AntPathMatcher matcher = new AntPathMatcher();
		System.out.println(matcher.match("resource:*", "resource:read"));
	}
}
