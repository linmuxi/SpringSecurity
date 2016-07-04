/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.factorybean;
import java.io.IOException;
import java.util.Properties;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

import com.hunter.common.utils.ApplicationContextUtil;


/**
 * 自定义缓存管理器工厂bean
 * 
 * @Description: 使用指定缓存配置文件,默认缓存管理器工厂对缓存文件路径有要求，无法提供配置路径方法,这里修改可以加载自定义的路径
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public class MyEhCacheManagerFactoryBean extends EhCacheManagerFactoryBean {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void setConfigLocation(String pathKey) {
		try {
			super.setConfigLocation(new ClassPathResource(
					((Properties) ApplicationContextUtil
							.getBean("propertiesFactoryBean")).getProperty(pathKey)));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置自定义缓存配置文件路径失败，"+e);
		}
	}

	public void afterPropertiesSet() throws IOException, CacheException {
		super.afterPropertiesSet();
	}

	public CacheManager getObject() {
		return super.getObject();
	}

}
