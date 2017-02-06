package com.quantifiedCare.webapp.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.quantifiedCare.util.common.QuantifiedCareUtil;
import com.quantifiedCare.util.constant.RequestMappingURL;
import com.quantifiedCare.util.constant.QuantifiedCareConstant;


/**
 * The Class .
 *
 * {@link QuantifiedCareExceptionHandler} is a implementation that allows for mapping exception class names to view names, either
 * for a set of given handlers or for all handlers in the DispatcherServlet.
 *
 * <p>
 * Error views are analogous to error page JSPs, but can be used with any kind of exception including any checked one,
 * with fine-granular mappings for specific handlers.
 */
public class QuantifiedCareExceptionHandler extends SimpleMappingExceptionResolver {

    /** The Constant EXCEPTION_STACK_TRACE. */
    private static final String EXCEPTION_STACK_TRACE = "excStackTrace";

    /** The Constant EXCEPTION_OCCUR_DATE. */
    private static final String EXCEPTION_OCCUR_DATE = "excDate";

    /** The Constant FQCN. */
    private static final String FQCN = QuantifiedCareExceptionHandler.class.getName();

    /** The Constant TARGET_DIV_ID. */
    private static final String TARGET_DIV_ID = "targetDiv";

    /** The Constant AJAX_EXCEPTION. */
    private static final String AJAX_EXCEPTION = "ajaxException";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(FQCN);

    /**
     * Gets the root cause.
     *
     * @param e
     *            the e
     * @return the root cause
     */
    private Exception getRootCause(Exception e) {
        while (e.getCause() instanceof Exception) {
            e = (Exception) e.getCause();
        }
        return e;
    }

    /**
     * Sets the attributes in request.
     *
     * @param request
     *            the request
     * @param ex
     *            the ex
     */
    private void setAttributesInRequest(final HttpServletRequest request, final Exception ex) {
        request.setAttribute(EXCEPTION_STACK_TRACE, ex.getStackTrace());
        request.setAttribute(EXCEPTION_OCCUR_DATE, new Date());
    }

    @Override
    protected void logException(final Exception ex, final HttpServletRequest request) {
        if (ex instanceof RuntimeException) {
        	  RuntimeException runTimeExc = (RuntimeException) ex;
              QuantifiedCareExceptionHandler.LOGGER.error(runTimeExc.getMessage(), runTimeExc);
        } else if (ex instanceof Exception) {
            Exception baseException =  ex;
            LOGGER.error(baseException.getMessage(), baseException);
        } else {
            QuantifiedCareExceptionHandler.LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    protected ModelAndView doResolveException(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler, final Exception ex) {
        ModelAndView modelAndView = null;
        Exception exc = this.getRootCause(ex);
        this.setAttributesInRequest(request, exc);
        if (exc instanceof AccessDeniedException) {
            modelAndView = new ModelAndView(QuantifiedCareUtil.concat(QuantifiedCareConstant.REDIRECT, RequestMappingURL.APP_ERROR_VIEW_URL));
        }  else if (exc instanceof CannotAcquireLockException) {
            modelAndView = new ModelAndView(QuantifiedCareUtil.concat(QuantifiedCareConstant.REDIRECT, RequestMappingURL.TRANSACTION_ABORT_ERROR_VIEW_URL));
        } else if (QuantifiedCareUtil.isAjaxRequest(request)) {
            String targetId = request.getParameter(TARGET_DIV_ID);
            modelAndView = super.doResolveException(request, response, handler, this.handleAjaxRequest(targetId, exc,
                response));
            if (exc instanceof CannotAcquireLockException) {
                modelAndView.addObject("isTxAbort", true);
            }
        } else if (exc instanceof RuntimeException) {
        	modelAndView = new ModelAndView(QuantifiedCareUtil.concat(QuantifiedCareConstant.REDIRECT, RequestMappingURL.ERROR_VIEW_URL));
        } else {
            modelAndView = super.doResolveException(request, response, handler, exc);
        }
        return modelAndView;
    }

    /**
     * Handle ajax request on ` of exception in system.
     *
     * @param targetId the target id
     * @param exc the exc
     * @param resp the resp
     * @return the ajax exception
     */
    private Exception handleAjaxRequest(final String targetId, final Exception exc,
        final HttpServletResponse resp) {
        Exception ajaxExceptionInstance = null;
        if (exc instanceof Exception) {
            ajaxExceptionInstance =  exc;
            resp.setHeader(AJAX_EXCEPTION, String.valueOf(1));
        } 
        return ajaxExceptionInstance;
    }
}
