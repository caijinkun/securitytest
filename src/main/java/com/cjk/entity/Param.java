package com.cjk.entity;

import java.util.HashMap;

public class Param extends HashMap<String, Object>{
	public Param append(String key, Object value){
		this.put(key, value);
		return this;
	}
}
