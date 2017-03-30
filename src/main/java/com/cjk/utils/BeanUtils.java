package com.cjk.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    public static Map<String, Object> bean2Map(Object... objs) throws Exception {
    	Map<String, Object> result = new HashMap<>();
    	
    	if(objs == null){
    		return result;
    	}
		for (int i = 0, len = objs.length; i < len; i++) {         
	        Map<String, Object> map = new HashMap<String, Object>();  
            BeanInfo beanInfo = Introspector.getBeanInfo(objs[i].getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                if (!key.equals("class")) {  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(objs[i]);  
                    map.put(key, value);  
                }  
            }  
            result.putAll(map);
        }
		return result;
    }
}
