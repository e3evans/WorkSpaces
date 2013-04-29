package com.manpower.translations.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * {@link ActionSupport} that simplifies getting context parameters
 */
public class ExtendedActionSupport extends ActionSupport
    implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = -3818704625612497066L;
    protected HttpServletRequest req;
    protected HttpServletResponse response;

    /** @see ServletRequestAware#setServletRequest(HttpServletRequest) */
    public final void setServletRequest(final HttpServletRequest arg0) {
        req = arg0;
    }

    /** @return the {@link HttpServletRequest} */
    protected final HttpServletRequest getServletRequest() {
        return req;
    }

    /** @see com.opensymphony.xwork2.ActionSupport#execute() */
    @Override
    public String execute() throws Exception {
        if(req.getMethod().equals(HttpMethod.POST)) {
            return post();
        } else if(req.getMethod().equals(HttpMethod.GET)) {
            return get();
        }
        return ERROR;
    }

    /** @see com.opensymphony.xwork2.ActionSupport#validate() */
    @Override
    public void validate() {
        if(req.getMethod().equals(HttpMethod.POST)) {
            validatePost();
            return;
        } else if(req.getMethod().equals(HttpMethod.GET)) {
            validateGet();
            return;
        }
        return;
    }

    /** validate the correct status in case there is a post */
    protected void validateGet() {

    }

    /** validate the correct status in case there is a post */
    protected void validatePost() {

    }

    /** handles a get and returns a view name */
    protected String get() {
        return SUCCESS;
    }

    /** handles a post and returns a view name */
    protected String post() {
        return SUCCESS;
    }

    /** @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse) */
    public void setServletResponse(final HttpServletResponse httpservletresponse) {
        this.response = httpservletresponse;

    }
    
}