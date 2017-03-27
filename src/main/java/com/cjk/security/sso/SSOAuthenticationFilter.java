package com.cjk.security.sso;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import com.cjk.constant.SSOConstant;
import com.cjk.security.sso.Result.AccessTokenData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SSOAuthenticationFilter extends GenericFilterBean{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		if(StringUtils.isBlank(request.getParameter(SSOConstant.ACCESS_TOKEN))){
			return;
		}
		
		Result result = postToSSOPlatform(request);
		if(null == result || !result.validate()){
			return;
		}
		
		AccessTokenData tokenData = result.getData().getAccessTokenData();
		String mallId = tokenData.getMallId();
		Authentication authentication = new SSOAuthenticationToken(mallId, "");
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	//TODO
	protected Result postToSSOPlatform(HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter(SSOConstant.ACCESS_TOKEN);
		
		String url = "http://betassl2.healthmall.cn/data/appserver/api/validateToken.do";
		String charset = "UTF-8";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		EntityBuilder entityBuilder= EntityBuilder.create().setContentEncoding(charset)
				.setContentType(ContentType.create("application/x-www-form-urlencoded", Charset.forName(charset)));
		
		List<NameValuePair> parameters=new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(SSOConstant.ACCESS_TOKEN, accessToken));
		entityBuilder.setParameters(parameters);
		
		HttpEntity entity = entityBuilder.build();
		post.setEntity(entity);
		
		HttpResponse res = client.execute(post);
		InputStream inputStream = res.getEntity().getContent();
		String resultStr = IOUtils.toString(inputStream, charset);
		ObjectMapper mapper = new ObjectMapper();
		Result result = mapper.readValue(resultStr, Result.class);
		
		return result;
	}
	
	
	
	public static void main(String[] args) throws Exception {
//		String url = "http://betassl2.healthmall.cn/data/appserver/api/validateToken.do";
//		String charset = "UTF-8";
//		
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(url);
//		EntityBuilder entityBuilder= EntityBuilder.create().setContentEncoding(charset)
//				.setContentType(ContentType.create("application/x-www-form-urlencoded", Charset.forName(charset)));
//		
//		List<NameValuePair> parameters=new ArrayList<NameValuePair>();
//		parameters.add(new BasicNameValuePair(SSOConstant.ACCESS_TOKEN, "7454432D32695A7A7942654E75382D414153417146386634353064432D4C7176786F31343933755476347A676E452D6A597A47675041596C6B7A5174685236756D5A43747833314A516D727041756452386C494F45672E2E"));
//		entityBuilder.setParameters(parameters);
//		
//		HttpEntity entity = entityBuilder.build();
//		post.setEntity(entity);
//		
//		HttpResponse res = client.execute(post);
//		InputStream inputStream = res.getEntity().getContent();
//		String result = IOUtils.toString(inputStream, charset);
		
		String src = "{\"code\":2000,\"msg\":\"操作成功\",\"data\":{\"appId\":null,\"accessTokenData\":{\"recId\":\"3497602\",\"mallId\":\"997192714\",\"telephone\":\"13000112233\",\"token\":\"4B24vFhbg8\",\"userName\":\"\",\"openId\":\"\",\"unionId\":\"\",\"userType\":\"2\"}}}";
	
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.readValue(src, Result.class));
	}
}
