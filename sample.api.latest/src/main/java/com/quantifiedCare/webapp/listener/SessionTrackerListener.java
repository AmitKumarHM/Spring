/**
 * 
 */
package com.quantifiedCare.webapp.listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionTrackerListener implements HttpSessionListener{

	/** The sessions. */
    private static Set<HttpSession> sessions = new HashSet<HttpSession>();

   
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessions.add(httpSessionEvent.getSession());
    }

    
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessions.remove(httpSessionEvent.getSession());

    }

    /**
     * Gets the sessions.
     * 
     * @return the sessions
     */
    public static List<HttpSession> getSessions() {
        List<HttpSession> sessionsList = new ArrayList<HttpSession>(sessions);
        return sessionsList;
    }
}
