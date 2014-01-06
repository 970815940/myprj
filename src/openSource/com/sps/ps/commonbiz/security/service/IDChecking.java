package com.sps.ps.commonbiz.security.service;

/**
 * 身份确认接口  <p/><b>登陆时使用该接口</b>
 * @author taoxs
 *
 */
public interface IDChecking {
	/**
	 * 验证成功 返回true  否则返回 false
	 * @param loginid
	 * @param password
	 * @return
	 */
	public boolean validateUser(String loginid,String password);
	/**
	 * 验证密码是否为MD5； 如果是则返回true  否则返回false
	 * @param loginId
	 * @return
	 */
	public boolean isMD5(String loginId);
}
