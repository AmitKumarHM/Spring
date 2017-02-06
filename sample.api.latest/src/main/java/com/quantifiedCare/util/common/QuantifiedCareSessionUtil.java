package com.quantifiedCare.util.common;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The Class QuantifiedCareSessionUtil. It is a utility class to get session object or any object/value from session object using
 * its key
 */
/**
 * @author Arvind
 *
 */
public final class QuantifiedCareSessionUtil {

    /**
     * Instantiates a new session util.
     */
    private QuantifiedCareSessionUtil() {
    }

    /**
     * Session. Provides the HttpSession object.
     *
     * @return the http session
     */
    public static HttpSession getSession() {
        HttpSession currentSession = null;
        if (null != RequestContextHolder.getRequestAttributes()) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            currentSession = attr.getRequest().getSession(true);
        }
        return currentSession;
    }

    /**
     * Gets the stored object from session using object key.
     *
     * @param <T>
     *            the generic type
     * @param classType
     *            the class type
     * @param key
     *            the key
     * @return the object from session
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectFromSession(final Class<T> classType, final String key) {
        T object = null;
        if (null != QuantifiedCareSessionUtil.getSession()) {
            Object sessionObj = QuantifiedCareSessionUtil.getSession().getAttribute(key);
            object = classType.isInstance(sessionObj) ? (T) sessionObj : null;
        }
        return object;
    }
}
