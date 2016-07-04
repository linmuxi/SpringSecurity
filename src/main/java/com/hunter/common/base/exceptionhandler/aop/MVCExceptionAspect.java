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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunter.common.base.exceptionhandler.annotation.ExceptionMessage;
import com.hunter.common.base.exceptionhandler.exception.SpringMVCException;
import com.hunter.common.base.exceptionhandler.handler.CurrentException;

/**
 * mvc异常切面
 * 
 * @Description: 处理所有mvc请求异常
 * @author linyong
 * @since 2015年5月17日 下午12:12:54
 */
@Service("mvcExceptionAspect")
public class MVCExceptionAspect {
	
	public Object doAround(ProceedingJoinPoint pjp) throws Exception {
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

			log(objLogger, exMessage, throwable);

			ResponseBody responseBody = (ResponseBody) method.getAnnotation(ResponseBody.class);
			boolean isAjax = false;
			if (responseBody != null) {
				isAjax = true;
			}

			throw new SpringMVCException(exMessage, throwable,Boolean.valueOf(isAjax));
		}

		return objResult;
	}

	private void log(Logger objLogger, String exMessage, Throwable throwable) {
		StackTraceElement[] elements = throwable.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement element = elements[i];
			if (element.getClassName().indexOf(".facade.") >= 0) {
				return;
			}
		}
		objLogger.error(exMessage, throwable);
	}
}