/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.autoscan;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 自定义mapper扫描类
 * 
 * @Description: 将所有SQL结尾的mapper配置文件加载进来
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public class MyBatisMapperScanner {

	private static final Logger logger = LoggerFactory
			.getLogger(MyBatisMapperScanner.class);

	public Resource[] scan(String packageName) {
		if (StringUtils.isEmpty(packageName)) {
			return null;
		}
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packageSearchPath = "classpath*:"
				+ packageName.replace('.', '/') + "/" + "**/*SQL.xml";
		Resource[] resources = null;
		try {
			resources = resourcePatternResolver.getResources(packageSearchPath);
		} catch (IOException ex) {
			logger.error("扫描获取mapper失败", ex);
		}

		if (resources != null) {
			int i = 0;
			for (int length = resources.length; i < length; i++) {
				Resource resource = resources[i];
				try {
					URI uri = new URI(resource.getURL().getProtocol() + ":///"
							+ resource.getURL().getFile());
					resources[i] = new UrlResource(uri);
				} catch (URISyntaxException ex) {
					logger.error("无法获取URI资源:" + resource, ex);
				} catch (IOException ex) {
					logger.error("无法读取URI资源:" + resource, ex);
				}
			}
		}
		return resources;
	}
}
