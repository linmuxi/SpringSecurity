/*
 * Copyright 2015 the original author or phly.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.model;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 扩展用户信息
 * @Description 扩展字段
 * @author linyong
 * @since 2015年5月17日 下午8:10:02
 */
public interface DefaultUserDetails extends UserDetails {
	String getAccount();
}
