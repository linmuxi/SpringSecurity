/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统权限VO
 * 
 * @Description TODO
 * @author linyong
 * @since 2015年5月17日 下午5:05:21
 */
public class SysAuthorityVO extends BaseVO {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String authorityId;
	private String authorityMark;
	private String authorityName;
	private String authorityDesc;
	private String message;
	private boolean enable;
	private String roleId;
	
	private Timestamp createDate;

	private String creator;

	private Timestamp lastUpdateDate;

	private String lastUpdater;
	
	private int isDel;

	private List<SysResourceVO> resources;
	
	public List<SysResourceVO> getResources() {
		return resources;
	}

	public void setResources(List<SysResourceVO> resources) {
		this.resources = resources;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityMark() {
		return authorityMark;
	}

	public void setAuthorityMark(String authorityMark) {
		this.authorityMark = authorityMark;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
}
