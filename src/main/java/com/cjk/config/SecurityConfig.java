package com.cjk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cjk.security.SimpleAccessDecisionManager;
import com.cjk.security.SimpleFilterInvocationSecurityMetadataSource;
import com.cjk.security.SimpleSecurityInterceptor;
import com.cjk.security.SimpleUserDetailsService;
import com.cjk.security.TestAuthenticationProvider;
import com.cjk.security.sso.SSOAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Bean
	public SimpleUserDetailsService simpleUserDetailsService(){
		return new SimpleUserDetailsService();
	}
	
	@Bean
	public SimpleFilterInvocationSecurityMetadataSource simpleFilterInvocationSecurityMetadataSource(){
		return new SimpleFilterInvocationSecurityMetadataSource();
	}
	
	@Bean
	public SimpleAccessDecisionManager simpleAccessDecisionManager(){
		return new SimpleAccessDecisionManager();
	}
	
	@Bean
	public SimpleSecurityInterceptor simpleSecurityInterceptor(){
		return new SimpleSecurityInterceptor(simpleAccessDecisionManager(), authenticationManager,
				simpleFilterInvocationSecurityMetadataSource());
	}
	@Bean
	public SSOAuthenticationFilter SSOAuthenticationFilter(){
		return new SSOAuthenticationFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/index");
		
//		http
//			.addFilterBefore(SSOAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//			.addFilterBefore(simpleSecurityInterceptor(), FilterSecurityInterceptor.class)
		
		http
			.authorizeRequests()  
        	.antMatchers("/static/**", "/login").permitAll()
        	.anyRequest().authenticated()                
	        .and()
	        	.csrf().disable()
	        	.formLogin()  
	        	.loginPage("/login")
	        	.usernameParameter("username").passwordParameter("password")
	        	.successHandler(successHandler)         
	        .and()  
	        	.logout().logoutSuccessUrl("/index")                     
	        	.invalidateHttpSession(true)  
	        .and()  
	        	.rememberMe()                                  
	        	.tokenValiditySeconds(1209600);  
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(testAuthenticationProvider());
		auth.userDetailsService(simpleUserDetailsService());
	}
	
	@Bean
	public TestAuthenticationProvider testAuthenticationProvider(){
		return new TestAuthenticationProvider();
	}
}
