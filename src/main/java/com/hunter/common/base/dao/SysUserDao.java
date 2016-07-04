/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.hunter.common.base.model.SysAuthorityVO;
import com.hunter.common.base.model.SysRoleVO;
import com.hunter.common.base.model.SysUserVO;

/**
 * 系统用户dao类
 * 
 * @Description TODO
 * @author linyong
 * @since 2015年5月17日 下午4:51:46
 */
@Repository
public class SysUserDao extends MyBatisDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 根据用户帐号获取用户信息
	 * 
	 * @param account
	 * @return
	 */
	public SysUserVO getUserByAccount(String account) {
		return (SysUserVO) this.getObject("sysUser.getUserByAccount", account);
	}
	
	/**
	 * 查询用户列表
	 * 
	 * @param userVO
	 * @return 用户集合
	 */
	public List<SysUserVO> queryUserList(SysUserVO userVO) {
		return this.queryList("sysUser.queryUserList", userVO,
				userVO.getPageNo(), userVO.getPageSize());
	}

	/**
	 * 获取用户总数
	 * @param userVO
	 * @return
	 */
	public int getUserCount(SysUserVO userVO){
		return (Integer) this.getObject("sysUser.getUserCount", userVO);
	}
	
	/**
	 * 根据用户名称或id获取用户vo
	 * @param userVO
	 * @return 
	 */
	public SysUserVO getUser(SysUserVO userVO) {
		return (SysUserVO) this.getObject("sysUser.getUser", userVO);
	}
	
	
	/**
	 * 根据用户名称或id获取用户vo
	 * @param userId
	 * @return 
	 */
	public SysUserVO getUserIncludAllByUserId(SysUserVO userVO) {
		return (SysUserVO) this.getObject("sysUser.getUserIncludAllByUserId", userVO);
	}

	/**
	 * 添加用户
	 * @param userVO
	 * @return
	 */
	public boolean addUser(SysUserVO userVO) {
		boolean flag = false;
		try {
			this.insert("sysUser.addUser",userVO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加用户失败,"+e);
		}
		return flag;
	}
	
	/**
	 * 修改用户
	 * @param userVO
	 * @return
	 */
	public boolean updateUser(SysUserVO userVO) {
		boolean flag = false;
		try {
			this.update("sysUser.updateUser",userVO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改用户失败,"+e);
		}
		return flag;
	}
	
	/**
	 * 删除用户
	 * @param userVO
	 * @return
	 */
	public boolean delUser(SysUserVO userVO) {
		boolean flag = false;
		try {
			this.update("sysUser.delUser",userVO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户失败,"+e);
		}
		return flag;
	}

	/**
	 * 根据用户帐号获取到用户的权限并封装成GrantedAuthority集合
	 * 
	 * @param account
	 * @return
	 */
	public Collection<GrantedAuthority> loadUserAuthorities(String account) {
		List<SysAuthorityVO> list = this.getSysAuthoritiesByAccount(account);
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (SysAuthorityVO authority : list) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
						authority.getAuthorityMark());
				auths.add(grantedAuthority);
			}
		}
		return auths;
	}

	/**
	 * 先根据用户名获取到SysAuthorities集合
	 * 
	 * @param account
	 * @return 权限集合
	 */
	private List<SysAuthorityVO> getSysAuthoritiesByAccount(String account) {
		return this.queryList("sysUser.getSysAuthoritiesByAccount", account);
	}

	/**
	 * 获取所有资源和权限映射数据 
	 * @return 资源权限集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, String>> queryResourceAuthority() {
		List list = this.queryList("sysUser.queryResourceAuthority", null);
		if (CollectionUtils.isNotEmpty(list)) {
			List<Map<String, String>> rsMapList = new ArrayList<Map<String, String>>();
			for (Object object : list) {
				Map<String,String> rsMap = (Map<String, String>) object;
				Map<String, String> map = new HashMap<String, String>();
				map.put("resourcePath", (String) rsMap.get("RESOURCE_PATH"));
				map.put("authorityMark", (String) rsMap.get("AUTHORITY_MARK"));
				rsMapList.add(map);
			}
			return rsMapList;
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 保存用户登录ip和时间
	 * @param pUser
	 * @return
	 */
	public boolean saveUserLoginIpAndDate(SysUserVO pUser){
		boolean flag = false;
		try {
			this.update("sysUser.saveUserLoginIpAndDate", pUser);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户登录ip和时间失败",e);
		}
		return flag;
	}

	/**
	 * 保存用户退出系统时间
	 * @param pUser
	 * @return
	 */
	public boolean saveUserLogoutDate(SysUserVO pUser){
		boolean flag = false;
		try {
			//this.update("sysUser.saveUserLogoutDate", pUser); 数据库中需要新增退出系统时间字段
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户退出系统时间失败",e);
		}
		return flag;
	}
	
	/**
	 * 查看自己拥有的角色
	 * @param userVO
	 * @return
	 */
	public List<SysRoleVO> getSelfRole(SysUserVO userVO){
		return this.queryList("sysUser.getSelfRole", userVO);
	}

	/**
	 * 查看自己未拥有的角色
	 * @param userVO
	 * @return
	 */
	public List<SysRoleVO> getNoSelfRole(SysUserVO userVO){
		return this.queryList("sysUser.getNoSelfRole", userVO);
	}
	
	/**
	 * 添加用户角色关系数据
	 * @param userVO
	 * @return
	 */
	public boolean addUserRole(SysUserVO userVO){
		boolean flag = false;
		try {
			this.delete("sysUser.delUserRole", userVO);
			List<SysRoleVO> roles = userVO.getRoles();
			if(null != roles && roles.size() > 0){
				for (SysRoleVO sysRoleVO : roles) {
					sysRoleVO.setUserId(userVO.getUserId());
					this.insert("sysUser.addUserRole", sysRoleVO);
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加用户角色关系数据失败",e);
		}
		return flag;
	}

}
