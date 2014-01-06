package com.sps.ps.commonbiz.security.entity;

import com.sps.ps.core.web.ClientProxy;

/**
 * 登录信息上下文
 * @author taoxs
 *
 */
public class LoginContext {
	public final static String LOGIN_SESSION_KEY="com_ing_loginUser";
	public final static String LOGIN_COOKIE_KEY="com.sysps.login";
	/***
	 * 写入登录信息到session
	 * @param cp
	 * @return
	 */
	public static LoginUser getLoginUser(ClientProxy cp){
		Object obj=cp.getSession().getAttribute(LOGIN_SESSION_KEY);
		
		return obj!=null?(LoginUser)obj:null;
	}
	/***
	 * 退出登录
	 */
	public static void removeLoginUser(ClientProxy cp){
		cp.removeAttribute(LOGIN_SESSION_KEY, cp.HTTP_SESSION);
	}
}
