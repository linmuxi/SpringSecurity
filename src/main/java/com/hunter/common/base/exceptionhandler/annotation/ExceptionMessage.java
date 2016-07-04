/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.exceptionhandler.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 异常注解
 * 
 * @Description: 在方法上定义抛出的异常信息
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionMessage {
	public abstract String value();
}
