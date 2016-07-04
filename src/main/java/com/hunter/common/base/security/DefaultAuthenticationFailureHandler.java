/*
 * Copyright 2015 the original author or hunter.
 * 未经正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.hunter.common.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

/**
 * 自定义验证失败处理器
 * @Description 自己处理登录验证失败后的处理操作，自定义登录异常信息
 * @author linyong
 * @since 2015年5月19日 下午10:43:05
 */
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);
	
    private String defaultFailureUrl;
    private boolean forwardToDestination = false;
    private boolean allowSessionCreation = true;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     *  "User not exists"  --帐号不存在
		"Bad credentials" --失败的凭证(密码错误)
		"User account is locked" --账户被锁
		"User is disabled" --账户失效
		"User account has expired" --账户过期
		"User credentials have expired" --凭证过期(密码过期)
     */
    private static final String[] authenticationExceptions = {"User not exists","Bad credentials",
    	"User account is locked","User is disabled","User account has expired","User credentials have expired"};

    public DefaultAuthenticationFailureHandler() {
    }

    public DefaultAuthenticationFailureHandler(String defaultFailureUrl) {
        setDefaultFailureUrl(defaultFailureUrl);
    }

    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise returns a 401 error code.
     * <p>
     * If redirecting or forwarding, {@code saveException} will be called to cache the exception for use in
     * the target view.
     */
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
    	//如果没有配置默认跳转失败url，则返回json格式,否则跳转到配置的url去
        if (defaultFailureUrl == null) {
            response.setCharacterEncoding("UTF-8");  
            response.getWriter().print("{\"flag\":0,\"data\":{\"error\":\""+exception.getMessage()
            		+"\",\"code\":\""+getAuthenticationExceptionCode(exception.getMessage())+"\"}}");
        } else {
            saveException(request, exception);

            if (forwardToDestination) {
                logger.debug("Forwarding to " + defaultFailureUrl);

                request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
            } else {
                logger.debug("Redirecting to " + defaultFailureUrl);
                redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
            }
        }
    }
    
    /**
     * 根据认证异常信息返回对应编码
     * @param message
     * @return @see authenticationExceptions 变量的注释
     */
    private int getAuthenticationExceptionCode(String message){
    	for(int i = 1;i<=authenticationExceptions.length;i++){
			if(StringUtils.equalsIgnoreCase(authenticationExceptions[i-1], message)){
				return i;
			}
    	}
    	return -1;
    }
    
    /**
     * The URL which will be used as the failure destination.
     *
     * @param defaultFailureUrl the failure URL, for example "/loginFailed.jsp".
     */
    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl),
                "'" + defaultFailureUrl + "' is not a valid redirect URL");
        this.defaultFailureUrl = defaultFailureUrl;
    }
    
    /**
     * Caches the {@code AuthenticationException} for use in view rendering.
     * <p>
     * If {@code forwardToDestination} is set to true, request scope will be used, otherwise it will attempt to store
     * the exception in the session. If there is no session and {@code allowSessionCreation} is {@code true} a session
     * will be created. Otherwise the exception will not be stored.
     */
    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
        if (forwardToDestination) {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        } else {
            HttpSession session = request.getSession(false);

            if (session != null || allowSessionCreation) {
                request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
            }
        }
    }
}
