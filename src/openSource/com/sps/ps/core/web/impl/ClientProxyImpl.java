package com.sps.ps.core.web.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sps.ps.core.web.ClientProxy;



public class ClientProxyImpl implements ClientProxy {
	private static Logger log = Logger.getLogger(ClientProxyImpl.class);
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext   servletContext;

	public ServletContext getApplication() {
		
		return servletContext;
	}

	public ClientProxyImpl( HttpServletRequest request,HttpServletResponse response,HttpSession session,
			 ServletContext servletContext) {
		super();
		this.session = session;
		this.request = request;
		this.response = response;
		this.servletContext = servletContext;
	}

	public Object getAttribute(String key, byte scope) {
		Object obj=null;
		switch (scope) {
		case 0:
			obj=request.getAttribute(key);
			break;
		case 2:
			obj=session.getAttribute(key);
			break;	
		case 3:
			obj=servletContext.getAttribute(key);
			break;
		}
		return obj;
	}

	public Object getAttribute(String key, byte scope, String defaultValue) {
		Object obj=getAttribute(key,scope);
		return obj==null?defaultValue:obj;
	}

	public Object getAttribute(String key, String defaultValue) {
		Object obj=getAttribute(key,this.HTTP_REQUEST);
		return obj==null?defaultValue:obj;
	}

	public Object getAttribute(String key) {
		Object obj=getAttribute(key,this.HTTP_REQUEST);
		return obj;
	}

	public String getContextRealPath() {
		
		return getContextRealPath("/");
	}

	public String getContextRealPath(String relativePath) {
		String str=request.getSession().getServletContext().getRealPath(relativePath);
		return str;
	}

	public HttpServletRequest getRequest() {
		
		return request;
	}

	public HttpServletResponse getResponse() {
		
		return response;
	}

	public HttpSession getSession() {
		
		return session;
	}

	public void setAttribute(String key, Object value, byte scope) {
		switch (scope) {
		case 0:
			request.setAttribute(key, value);
			break;
		case 2:
			session.setAttribute(key, value);
			break;
		case 3:
			servletContext.setAttribute(key, value);
			break;			
		}
		
	}

	public void setAttribute(String key, Object value) {
		setAttribute(key,value,this.HTTP_REQUEST);
	}

	
	
	
	
	
	
	
	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @param servletContext the servletContext to set
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void removeAttribute(String key,byte scope) {
		switch (scope) {
		case 0:
				this.request.removeAttribute(key);
			break;
		case 2:
				this.session.removeAttribute(key);
			break;	
		case 3:
				this.servletContext.removeAttribute(key);
			break;			
		}
		
	}

	public String getPar(String key) {
		
		return this.request.getParameter(key);
	}

	public String getPar(String key, String DefaultValue) {
		String value=getPar(key);
		return value==null?DefaultValue:value;
	}

	public String getContextPath() {
		
		return this.request.getContextPath();
	}

}
