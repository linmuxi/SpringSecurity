/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.exceptionhandler.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hunter.common.base.exceptionhandler.annotation.ExceptionMessage;
import com.hunter.common.base.exceptionhandler.handler.CurrentException;

/**
 * dwr异常切面
 * 
 * @Description: 处理所有dwr请求异常
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
@Service("dwrExceptionAspect")
public class DWRExceptionAspect {
	
	public Object doAround(ProceedingJoinPoint pjp) {
		Logger objLogger = LoggerFactory.getLogger(pjp.getTarget().getClass());
		Object objResult = null;
		try {
			objResult = pjp.proceed();
		} catch (Throwable throwable) {
			Method method = ((MethodSignature) pjp.getSignature()).getMethod();
			ExceptionMessage ex = (ExceptionMessage) method.getAnnotation(ExceptionMessage.class);
			String exMessage = null;
			if (ex != null)
				exMessage = ex.value();
			else if (CurrentException.getExceptionMessage() != null)
				exMessage = CurrentException.getExceptionMessage();
			else if (StringUtils.hasText(throwable.getMessage()))
				exMessage = throwable.getMessage();
			else {
				exMessage = "操作失败、服务器异常";
			}

			CurrentException.clearCurrentExcetpion();

			objLogger.error(exMessage, throwable);

			throw new RuntimeException(exMessage, throwable);
		}

		return objResult;
	}
}
