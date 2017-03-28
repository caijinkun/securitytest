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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import com.cjk.constant.SSOConstant;
import com.cjk.security.sso.Result.AccessTokenData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SSOAuthenticationFilter extends GenericFilterBean{
	private UserDetailsService userDetailsService;
	
	public SSOAuthenticationFilter(UserDetailsService userDetailsService){
		this.userDetailsService = userDetailsService;
	}

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
		UserDetails userDetail = userDetailsService.loadUserByUsername(mallId);
		
		
//		Authentication authentication = new SSOAuthenticationToken(mallId, "");
		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
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
}
