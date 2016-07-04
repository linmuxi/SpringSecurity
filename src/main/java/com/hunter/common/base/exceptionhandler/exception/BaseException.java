/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.exceptionhandler.exception;

/**
 * 自定义异常对象
 * 
 * @Description: 所有异常继承该异常
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
