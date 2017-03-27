package com.cjk.security.sso;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result {
	private Integer code;
	private String msg;
	private Data data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	//TODO
	public boolean validate(){
		if(2000 != this.getCode() || null == this.getData() || StringUtils.startsWith(this.getData().getAppId(), "VISTOR")){
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static class Data{
		private String appId;
		private AccessTokenData accessTokenData;
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public AccessTokenData getAccessTokenData() {
			return accessTokenData;
		}
		public void setAccessTokenData(AccessTokenData accessTokenData) {
			this.accessTokenData = accessTokenData;
		}
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	public static class AccessTokenData{
		private String recId;
		private String mallId;
		private String telephone;
		private String token;
		private String userName;
		private String openId;
		private String unionId;
		private String userType;
		public String getRecId() {
			return recId;
		}
		public void setRecId(String recId) {
			this.recId = recId;
		}
		public String getMallId() {
			return mallId;
		}
		public void setMallId(String mallId) {
			this.mallId = mallId;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getOpenId() {
			return openId;
		}
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public String getUnionId() {
			return unionId;
		}
		public void setUnionId(String unionId) {
			this.unionId = unionId;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}
}
