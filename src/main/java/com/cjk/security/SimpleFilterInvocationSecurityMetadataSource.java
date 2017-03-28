package com.cjk.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import com.cjk.service.ResourceService;

public class SimpleFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	@Autowired
	private ResourceService resourceService;
	
	private static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap = new HashMap<>();
	
	@PostConstruct
    public void init(){
    	List<Map<String, String>> perm2RoleList = resourceService.getPerm2RoleList();
    	Map<String, Collection<ConfigAttribute>> resourceTemp = new HashMap<>();
    	
    	Set<String> mark = new HashSet<>();    //标记
    	
    	for(Map<String, String> item:perm2RoleList){
    		String permision = item.get("permision");
    		String roleName = item.get("roleName");
			Collection<ConfigAttribute> attrSet;
			
    		if(mark.contains(permision)){
    			attrSet = resourceTemp.get(permision);
    		}else{
    			mark.add(permision);	       //标记已遍历该url
    			attrSet = new HashSet<>();
    			resourceTemp.put(permision, attrSet);
    		}
    		if(StringUtils.isNotBlank(roleName)){
    			attrSet.add(new SecurityConfig(roleName));
    		}
    	}
    	
    	for(Map.Entry<String,Collection<ConfigAttribute>> item : resourceTemp.entrySet()){
    		resourceMap.put(new AntPathRequestMatcher(item.getKey()), item.getValue());
    	}
    }
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        Set<ConfigAttribute> attrSet = new HashSet<>();
        
        for(Map.Entry<RequestMatcher, Collection<ConfigAttribute>> item: resourceMap.entrySet()){
        	if(item.getKey().matches(filterInvocation.getHttpRequest())){
        		attrSet.addAll(item.getValue());
        	}
        }
		return CollectionUtils.isEmpty(attrSet)?null:attrSet;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
