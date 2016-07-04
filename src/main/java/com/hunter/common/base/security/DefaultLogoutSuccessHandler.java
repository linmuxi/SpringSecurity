/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.hunter.common.base.dao.SysUserDao;

/**
 *
 * 自定义用户注销成功处理器
 * @Description 自己处理用户注销成功的处理操作，用于对前后台登录做区分。
 * @author linyong
 * @since 2015年5月20日 上午12:36:24
 */
public class DefaultLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	@Resource
	private SysUserDao userDao;
	
	//注销成功后跳转的url
	private String[]logoutUrl ;
	
	public String[] getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String[] logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication == null){
			return;
		}
		//保存用户退出时间
		// ...
		
		//如果允许使用默认url，并且配置了默认url，则登录成功后跳转到指定url路径。否则返回登录成功标识：1
		if(isAlwaysUseDefaultTargetUrl() && StringUtils.isNotEmpty(getDefaultTargetUrl())){
			handle(request, response, authentication);
		}else{
			response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{\"flag\":1}");
		}
	}
}
