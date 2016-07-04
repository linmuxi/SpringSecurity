/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.exceptionhandler.exception;

/**
 * springmvc自定义异常
 * 
 * @Description:
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
public class SpringMVCException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isAjax;

	public SpringMVCException() {
	}

	public SpringMVCException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SpringMVCException(String message, Throwable throwable,
			Boolean isAjax) {
		super(message, throwable);
		this.isAjax = isAjax.booleanValue();
	}

	public boolean isAjax() {
		return this.isAjax;
	}

	public void setAjax(boolean isAjax) {
		this.isAjax = isAjax;
	}
}
