package com.sps.ps.commonbiz.security.service;

import java.util.List;

public interface AuthorizeService {
	/**
	 * 获取已授权的模块实体
	 * @param orgGuid
	 * @return
	 */
	public String getAutorization(String orgGuid);
	/**
	 * 获取未授权的模块实体
	 * @param orgGuid
	 * @return
	 */
	public String getUnAutorization(String orgGuid);
	/**
	 * 授权
	 * @param orgGUID
	 * @param ModelEntityId
	 */
	public void saveAutorization(String orgGUID,String ModelEntityId);
	/***
	 * 取消权限
	 * @param orgGUID
	 * @param modelEntityId
	 */
	public void saveUnAutorization(String orgGUID,String modelEntityId);
}
