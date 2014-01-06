package com.sps.ps.core.web;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * web与后台交互数据代理接口
 * 2013-09-20 17:47:21 新增获取post参数的方法
 * @author taoxs
 *
 */
public interface ClientProxy {
	/**
	 * request域
	 */
	public static final byte HTTP_REQUEST=0; 
	public static final byte HTTP_RESPONSE=1;
	public static final byte HTTP_SESSION=2;
	public static final byte HTTP_APPLICATION=3;
	public HttpServletRequest getRequest();
	public HttpServletResponse getResponse();
	public ServletContext getApplication();
	public HttpSession getSession();
	
	public void setAttribute(String key,Object value,byte scope);
	public void setAttribute(String key,Object value);
	public Object getAttribute(String key,byte scope);
	public Object getAttribute(String key,byte scope,String defaultValue);
	public Object getAttribute(String key,String defaultValue);
	public Object getAttribute(String key);
	public String getContextRealPath();
	public String getContextRealPath(String relativePath);
	/***
	 *  删除指定key  
	 * @param key
	 * @param scope
	 */
	public void removeAttribute(String key,byte scope);
	/**
	 * 根据KEY获取参数值
	 * @param key
	 * @return
	 */
	public String getPar(String key);
	/**
	 * 根据key获取值,如果为空 则返回DefaultValue
	 * @param key
	 * @param DefaultValue
	 * @return
	 */
	public String getPar(String key,String DefaultValue);
	public String getContextPath();
}
