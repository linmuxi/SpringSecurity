/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hunter.common.base.dao.SysUserDao;
import com.hunter.common.base.model.SysUserVO;
import com.hunter.common.utils.ApplicationContextUtil;

/**
 * 自定义验证成功处理器
 * 
 * @Description 自己处理登录验证成功后的处理操作，用于对前后台登录做区分。
 * @author linyong
 * @since 2015年5月19日 下午10:12:17
 */
public class DefaultAuthenticationSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		AuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);

	@Resource
	private SysUserDao userDao;

	/*@Resource
	private MemberDao memberDao;*/
	
	/**
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.Authentication)
	 */
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//清除登录异常信息
		clearAuthenticationAttributes(request);
		// 保存用户登录信息
		//this.saveLoginInfo(request, authentication);
		
		// 如果允许使用默认url，并且配置了默认url，则登录成功后跳转到指定url路径。否则返回登录成功标识：1
		if (isAlwaysUseDefaultTargetUrl()
				&& StringUtils.isNotEmpty(getDefaultTargetUrl())) {
			handle(request, response, authentication);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
					"{\"flag\":1,\"data\":{\"username\":\""
							+ ((SysUserVO) authentication.getPrincipal())
									.getUsername() + "\"}}");
		}
		this.refreshAuthList();
	}

	private void refreshAuthList() {
		// 刷新缓冲权限列表
		URLFilterInvocationSecurityMetadataSource sec = (URLFilterInvocationSecurityMetadataSource) ApplicationContextUtil
				.getBean("securityMetadataSource");
		try {
			logger.info("刷新权限列表");
			sec.afterPropertiesSet();
		} catch (Exception e) {
			logger.info("刷新权限列表失败");
			e.printStackTrace();
		}
	}

	/**
	 * 保存登录用户的ip和时间
	 * @param request
	 * @param authentication
	 */
	public void saveLoginInfo(HttpServletRequest request,
			Authentication authentication) {
		SysUserVO user = (SysUserVO) authentication.getPrincipal();
		try {
			user.setLastLoginDate(new Date());
			user.setLastLoginIp(this.getIpAddress(request));
			this.userDao.saveUserLoginIpAndDate(user);
		} catch (DataAccessException e) {
			if (logger.isWarnEnabled()) {
				logger.info("保存用户登录信息到数据库操作失败，"+e);
			}
		}
	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * Removes temporary authentication-related data which may have been stored
	 * in the session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
