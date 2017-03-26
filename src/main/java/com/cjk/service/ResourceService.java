package com.cjk.service;

import java.util.List;
import java.util.Map;

import com.cjk.dto.ResourceNode;

public interface ResourceService {
	List<Map<String, String>> getPerm2RoleList();
	List<ResourceNode> getResourceTree();
}
