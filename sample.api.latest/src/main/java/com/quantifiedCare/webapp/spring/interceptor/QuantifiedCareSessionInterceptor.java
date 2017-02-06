package com.quantifiedCare.webapp.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quantifiedCare.util.constant.QuantifiedCareConstant;

/**
 * The Class QuantifiedCareSessionInterceptor. The purpose of this class is to handle the session timeout and initialize the header
 * with flag and its value to identify the session timeout on return of ajax calls.
 */
/**
 * @author Arvind
 *
 */
public class QuantifiedCareSessionInterceptor extends HandlerInterceptorAdapter {

    /** The Constant AJAX_REDIRECT_HEADER. */
    private static final String AJAX_REDIRECT_HEADER = "timeOut";

    /** The Constant for login URL. */
    private static final String LOGIN_URL_STR = "/login";

    /**
     * This method throws the exception which is actually thrown by Spring API.
     *
     * @param req the req
     * @param resp the resp
     * @param handler the handler
     * @return true, if successful
     * @throws Exception the exception
     */
    @Override
    public boolean preHandle(final HttpServletRequest req, final HttpServletResponse resp, final Object handler)
        throws Exception {
        String requestURI = StringUtils.trimToEmpty(req.getRequestURI());
        HttpSession session = req.getSession(true);
        if (null == session.getAttribute(QuantifiedCareConstant.USER_KEY) 
        		&&  StringUtils.contains(requestURI, LOGIN_URL_STR)) {
            resp.setHeader(AJAX_REDIRECT_HEADER, String.valueOf(1));
        }
        return Boolean.TRUE;
    }

}
