/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.hunter.common.base.model.SysUserVO;

/**
 * 访问决策管理器
 * 
 * @Description 对请求的资源所需要的权限做判断<br/>
 *              <P>
 *              Spring提供了3个决策管理器:<br/>
 *              AffirmativeBased 一票通过，只要有一个投票器通过就允许访问<br/>
 *              ConsensusBased 有一半以上投票器通过才允许访问资源<br/>
 *              UnanimousBased 所有投票器都通过才允许访问<br/>
 *              <p>
 * @author linyong
 * @since 2015年5月17日 下午7:07:13
 */
public class DefaultAccessDecisionManager extends AbstractAccessDecisionManager {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义访问策略
	 * 
	 * @param authentication
	 *            用户及用户权限信息
	 * @param object
	 * @param configAttributes
	 *            访问资源需要的权限
	 * @throws AccessDeniedException
	 * @throws InsufficientAuthenticationException
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication,
	 *      java.lang.Object, java.util.Collection)
	 */
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		SysUserVO user = (SysUserVO) authentication.getPrincipal();
		logger.info("访问资源的用户为" + user.getUsername());
		// 如果访问资源不需要任何权限则直接通过
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		// 遍历configAttributes看用户是否有访问资源的权限
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			// ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.trim().equals(ga.getAuthority().trim())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("没有权限访问！ ");
	}
}
