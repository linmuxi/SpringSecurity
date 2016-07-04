/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * 系统用户
 * @Description TODO
 * @author linyong
 * @since 2015年5月17日 下午4:45:27
 */
public class SysUserVO extends BaseVO implements DefaultUserDetails {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String account;
	private String password;
	private Date lastLoginDate;
	private Date logoutDate;
	private String lastLoginIp;
	private String orgId;
	private String orgName;
	/** 帐号是否没有过期 */
	private boolean accountNonExpired;
	/** 证书是否没有过期 */
	private boolean credentialsNonExpired;
	/** 帐号是否没有被锁 */
	private boolean accountNonLocked;
	/** 是否可用 */
	private boolean enabled;
	/****null为系统用户   0为供应商用户  1为会员用户  ******/
	private String userType; 
	private List<SysRoleVO> roles;
	private Collection<GrantedAuthority> authorities;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public List<SysRoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRoleVO> roles) {
		this.roles = roles;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return this.userName;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	/**
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	public SysUserVO() {
		super();
	}
	
	

	public SysUserVO(String userId) {
		this.userId = userId;
	}

	public SysUserVO(String userName, String account, String password,
			String orgId, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			boolean enabled,String userType) {
		super();
		this.userType = userType;
		this.userName = userName;
		this.account = account;
		this.password = password;
		this.orgId = orgId;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.enabled = enabled;
	}
}
