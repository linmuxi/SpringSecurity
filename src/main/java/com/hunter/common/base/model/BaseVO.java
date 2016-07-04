/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 基础VO
 * 
 * @Description: 所有VO继承此VO对象
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public class BaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String _id;

	protected String actionType;

	protected String[] idList;

	protected int rp;

	protected int pageNo = 1;

	protected int pageSize = 20;

	protected String sortFieldName;

	protected String sortType;

	protected Timestamp updateTime;

	protected String creator;

	protected Timestamp createDate;

	protected String updater;

	// 搜索关键字
	protected String searchPhrase;

	// 搜索关键字数组
	protected String[] searchList;

	// 是否支持搜索模糊
	protected boolean searchRegex;

	// 工作流：业务表单服务类,全路径
	protected String actFormService;

	// 工作流：业务表单页面URI
	protected String actFormPage;

	public String getActFormService() {
		return actFormService;
	}

	public void setActFormService(String actFormService) {
		this.actFormService = actFormService;
	}

	public String getActFormPage() {
		return actFormPage;
	}

	public void setActFormPage(String actFormPage) {
		this.actFormPage = actFormPage;
	}

	public String getCreator() {
		/*if (this.creator == null) {
			SysUserVO userVO = RequestContextUtil.getCurrentUser();
			if (userVO != null) {
				return userVO.getAccount();
			}
		}*/
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getUpdater() {
		/*if (this.updater == null) {
			SysUserVO userVO = RequestContextUtil.getCurrentUser();
			if (userVO != null) {
				return userVO.getAccount();
			}
		}*/
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String get_id() {
		return this._id;
	}

	public String getActionType() {
		return this.actionType;
	}

	public String[] getIdList() {
		return this.idList;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public String getSortFieldName() {
		return this.sortFieldName;
	}

	public String getSortType() {
		return this.sortType;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void set_id(String id) {
		this._id = id;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public boolean equals(Object objValue) {
		boolean bEqual = super.equals(objValue);
		if (super.equals(objValue))
			bEqual = true;
		else {
			bEqual = EqualsBuilder.reflectionEquals(this, objValue);
		}
		return bEqual;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String[] getSearchList() {
		return searchList;
	}

	public void setSearchList(String[] searchList) {
		this.searchList = searchList;
	}

	public boolean isSearchRegex() {
		return searchRegex;
	}

	public void setSearchRegex(boolean searchRegex) {
		this.searchRegex = searchRegex;
	}
}