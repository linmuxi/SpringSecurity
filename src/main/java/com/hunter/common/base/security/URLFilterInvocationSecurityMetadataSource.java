/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.hunter.common.base.dao.SysUserDao;

/**
 * 初始化资源，获取请求资源所需要的权限
 * 
 * @Description 从数据库查询资源权限数据
 * @author linyong
 * @since 2015年5月17日 下午6:17:22
 */
public class URLFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource, InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 资源对应权限集合
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	@Resource
	private SysUserDao sysUserDao;

	/**
	 * 根据request请求获取访问资源所需权限
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Collection<ConfigAttribute> attrs = Collections.emptyList();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {  
            if (entry.getKey().matches(request)) {
                attrs =  entry.getValue();
                break;
            }
        }
        logger.info("URI资源："+request.getRequestURI()+ " -> " + attrs);  
        return attrs;
	}

	/**
	 * @return
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();  
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {  
            allAttributes.addAll(entry.getValue());  
        }
        return allAttributes;  
	}

	/**
	 * @param clazz
	 * @return
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);  
	}

	/**
	 * @throws Exception
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();
		logger.info("资源权限列表" + this.requestMap);
	}

	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {
		Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		Map<String, String> resMap = this.loadResuorce();
		for (Map.Entry<String, String> entry : resMap.entrySet()) {
			String key = entry.getKey();
			map.put(new AntPathRequestMatcher(key), SecurityConfig.createListFromCommaDelimitedString(entry.getValue()));
		}
		return map;
	}

	/**
	 * 加载资源权限映射数据
	 * 如果一个资源对应多个权限，采用[资源名称：权限1,权限2]
	 * @return 资源权限集合
	 */
	private Map<String, String> loadResuorce() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Map<String, String>> list = this.sysUserDao.queryResourceAuthority();
		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> rs = it.next();
			String resourcePath = rs.get("resourcePath");
			String authorityMark = rs.get("authorityMark");
			if (map.containsKey(resourcePath)) {
				String mark = map.get(resourcePath);
				map.put(resourcePath, mark + "," + authorityMark);
			} else {
				map.put(resourcePath, authorityMark);
			}
		}
		return map;
	}

}
