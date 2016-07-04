/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.model;

import java.util.List;

/**
 * 系统角色VO
 * 
 * @Description TODO
 * @author linyong
 * @since 2015年5月17日 下午4:46:57
 */
public class SysRoleVO extends BaseVO {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String roleDesc;
	private int enable;
	private int isDel;
	private String userId;
	private List<SysModuleVO> modules;
	private List<SysAuthorityVO> auths;

	public List<SysModuleVO> getModules() {
		return modules;
	}

	public void setModules(List<SysModuleVO> modules) {
		this.modules = modules;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SysAuthorityVO> getAuths() {
		return auths;
	}

	public void setAuths(List<SysAuthorityVO> auths) {
		this.auths = auths;
	}
}
