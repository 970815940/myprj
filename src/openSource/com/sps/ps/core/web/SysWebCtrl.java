package com.sps.ps.core.web;
/**
 * 
 * struts web接口
 * @author taoxs
 * 2013年10月12日 16:59:18 新增下载成功类型
 */
public interface SysWebCtrl {
	/**
	 * 不分页
	 */
	public static final int ALLPAGE=-1;
	
	
	
	/**
	 * 普通AJAX请求成功
	 */
	public static final String AJAX_SUCCESS="ajaxsuccess";
	/**
	 * 新增数据成功
	 */
	public static final String ADD_SUCCESS="addsuccess";
	/**
	 * 修改数据成功
	 */
	public static final String UPDATE_SUCCESS="updatesuccess";
	/**
	 * 删除数据成功
	 */
	public static final String DELETE_SUCCESS="deletesuccess";
	/**
	 * 根据ID获取数据成功
	 */
	public static final String FIND_BY_OBJID_SUCCESS="findbyidsuccess";
	/**
	 * 获取LIST成功
	 */
	public static final String FIND_BY_OBJLIST_SUCCESS="findbylistsuccess";
	/**
	 * 附件上传成功
	 */
	public static final String UPLOAD_ATT_SUCCESS="uploadsuccess";
	/**
	 * 附件下载成功
	 */
	public static final String DOWNLOAD_ATT_SUCCESS="downloadsuccess";
	/**
	 * 返回普通字符串文件
	 */
	public static final String STRING="string";
	/**
	 * 返回单纯的对象集合 
	 * <b>注意:只是集合而已,不包含其他内容</b>
	 */
	public static final String SLIST="slist";
	/**
	 * 普通过滤器
	 */
	public void doFilter();
	/***
	 * 权限过滤器
	 * @return
	 */
	public boolean  doMRFilter();
}
