package com.quantifiedCare.webapp.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.js.ajax.tiles2.AjaxTilesView;

/**
 * Tiles view implementation that is able to handle partial rendering for Spring Javascript/Jquery Ajax requests.
 * 
 * This implementation uses the {@link SpringJavascriptAjaxHandler} by default to determine whether the current request
 * is an Ajax request. On an Ajax request, a "fragments" parameter will be extracted from the request in order to
 * determine which attributes to render from the current tiles view.
 * 
 */
/**
 * @author Arvind
 *
 */
public class QuantifiedCareAjaxTilesViewHandler extends AjaxTilesView {

    /*
     * (non-Javadoc)
     * @see org.springframework.js.ajax.tiles2.AjaxTilesView#getRenderFragments(java.util.Map,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected String[] getRenderFragments(final Map model, final HttpServletRequest request,
        final HttpServletResponse response) {
        return super.getRenderFragments(model, request, response);
    }

}
