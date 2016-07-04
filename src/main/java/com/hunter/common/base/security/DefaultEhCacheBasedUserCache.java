/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;

import com.hunter.common.base.model.DefaultUserDetails;

/**
 * 重新springsecurity默认缓存策略
 * @Description EhcacheBasedUserCache默认根据username缓存，这里我们修改成根据account缓存
 * @author linyong
 * @since 2015年5月17日 下午8:06:10
 */
public class DefaultEhCacheBasedUserCache extends EhCacheBasedUserCache {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEhCacheBasedUserCache.class);

	@Override
	public UserDetails getUserFromCache(String account) {
		Element element = getCache().get(account);
        if (logger.isDebugEnabled()) {
            logger.debug("Cache hit: " + (element != null) + "; account: " + account);
        }

        if (element == null) {
            return null;
        } else {
            return (UserDetails) element.getValue();
        }
	}

	@Override
	public void putUserInCache(UserDetails user) {
		DefaultUserDetails duser = (DefaultUserDetails) user;
        Element element = new Element(duser.getAccount(), duser);
        if (logger.isDebugEnabled()) {
            logger.debug("Cache put: " + element.getKey());
        }
        getCache().put(element);
	}

	@Override
	public void removeUserFromCache(UserDetails user) {
		DefaultUserDetails duser = (DefaultUserDetails) user;
        if (logger.isDebugEnabled()) {
            logger.debug("Cache remove: " + duser.getAccount());
        }

        this.removeUserFromCache(duser.getAccount());
	}

	@Override
	public void removeUserFromCache(String account) {
        getCache().remove(account);
	}
}
