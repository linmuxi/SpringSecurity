/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.exceptionhandler.handler;

/**
 * 存储当前线程异常信息
 * 
 * @Description: 存储当前线程异常信息
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public final class CurrentException {
	private static final ThreadLocal<String> CURRENTEXCETION = new ThreadLocal<String>();

	public static void setExceptionMessage(String message) {
		CURRENTEXCETION.set(message);
	}

	public static String getExceptionMessage() {
		return (String) CURRENTEXCETION.get();
	}

	public static void clearCurrentExcetpion() {
		CURRENTEXCETION.remove();
	}
}