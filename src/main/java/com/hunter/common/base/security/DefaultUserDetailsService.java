/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import com.hunter.common.base.dao.SysUserDao;
import com.hunter.common.base.model.SysUserVO;

/**
 * 用户认证
 * 
 * @Description 根据帐号加载用户权限信息
 * @author linyong
 * @since 2015年5月17日 下午5:23:28
 */
public class DefaultUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SysUserDao sysUserDao;

	/**
	 * 默认的登录验证DaoAuthenticationProvider中已经注入了缓存，这里就不再使用了，
	 * @see 注意AbstractUserDetailsAuthenticationProvider的148行，
	 * 如果当前用户状态异常，且是从缓存中获取的，就重新调用本对象的loadUserByUsername方法获取最新用户状态，
	 * 这样在修改用户状态后能实时更新过来.
	 */
	/*@Resource
	private UserCache userCache;*/

	/**
	 * 根据帐号查询用户权限信息
	 * 
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException {

		SysUserVO user = this.sysUserDao.getUserByAccount(account);
		if (user == null)
			throw new UsernameNotFoundException("User not exists",null);
		// 得到用户的权限
		Collection<GrantedAuthority> auths = this.sysUserDao.loadUserAuthorities(account);
		if (CollectionUtils.isEmpty(auths)) {
			// 按照Spring的标准如果没有任何权限也是要抛出这个异常的,在这里就不做判断了
			logger.info("该用户【" + account + "】没有配置任何权限!");
		}
		// 将权限设置到用户对象中
		user.setAuthorities(auths);
		logger.info("============" + account + "登录系统了,拥有的权限集合："+auths+"============");
		return user;
	}
}
