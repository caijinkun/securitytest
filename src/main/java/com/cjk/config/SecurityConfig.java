package com.cjk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.cjk.security.SimpleAccessDecisionManager;
import com.cjk.security.SimpleFilterInvocationSecurityMetadataSource;
import com.cjk.security.SimpleSecurityInterceptor;
import com.cjk.security.SimpleUserDetailsService;
import com.cjk.security.sso.SSOAuthenticationFilter;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
//	@Bean
//	public SimpleUserDetailsService simpleUserDetailsService(){
//		return new SimpleUserDetailsService();
//	}
//	
//	@Bean
//	public SimpleFilterInvocationSecurityMetadataSource simpleFilterInvocationSecurityMetadataSource(){
//		return new SimpleFilterInvocationSecurityMetadataSource();
//	}
//	
//	@Bean
//	public SimpleAccessDecisionManager simpleAccessDecisionManager(){
//		return new SimpleAccessDecisionManager();
//	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		SSOAuthenticationFilter ssoAuthenticationFilter = new SSOAuthenticationFilter(simpleUserDetailsService());
//		
//		
////		SimpleSecurityInterceptor simpleSecurityInterceptor = new SimpleSecurityInterceptor(
////				simpleAccessDecisionManager(), authenticationManager, simpleFilterInvocationSecurityMetadataSource());
//		
//		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//		successHandler.setDefaultTargetUrl("/index");
//		
////		http
////			.addFilterAfter(ssoAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////			.addFilterBefore(simpleSecurityInterceptor(), FilterSecurityInterceptor.class)
//		
//		http
//			.authorizeRequests()  
//        	.antMatchers("/static/**", "/login").permitAll()
//        	.antMatchers("/mytest/test1").hasRole("SUPER")
//        	.anyRequest().authenticated()                
//	        .and()
//	        	.csrf().disable()
//	        	.formLogin()  
//	        	.loginPage("/login")
//	        	.usernameParameter("username").passwordParameter("password")
//	        	.successHandler(successHandler)         
//	        .and()  
//	        	.logout().logoutSuccessUrl("/index")                     
//	        	.invalidateHttpSession(true);  
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.authenticationProvider(testAuthenticationProvider());
//		auth.userDetailsService(simpleUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
//	}
	
//	@Bean
//	public TestAuthenticationProvider testAuthenticationProvider(){
//		return new TestAuthenticationProvider();
//	}
}
