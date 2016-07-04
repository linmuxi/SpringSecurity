/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.utils;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * 信息摘要帮助类
 * @Description TODO
 * @author linyong
 * @since 2015年5月17日 下午4:45:27
 */
public class DigestUtil {

	/**
	 * 采用md5进行信息摘要加密
	 */
	public static String encodePasswordByMD5(String password,String key){
		return new MessageDigestPasswordEncoder("MD5").encodePassword(password,key);		
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtil.encodePasswordByMD5("123456", "th_sh"));
	}

}
