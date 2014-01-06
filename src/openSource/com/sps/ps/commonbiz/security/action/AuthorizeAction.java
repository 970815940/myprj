package com.sps.ps.commonbiz.security.action;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.security.service.AuthorizeService;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;

/**
 * 系统功能模块资源授权控制
 * @author taoxs
 *
 */
public class AuthorizeAction extends SysWebCtrlAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AuthorizeAction.class);
	private AuthorizeService authorizeServiceImpl;
	// ==========================================
	//priperties start
	private String orgGUID;//组织机构ID
	private String meIds;//实体功能表ID  需要用,分割 有可能存在多个
	// priperties end
	//============================================
	/**
	 * <b>为指定功能实体授权</b>
	 *<p>这里用到的参数有 orgGUID，meIds</p>
	 */
	public String doAuthorize(){
		try {
			super.smsg=new SimpleMsg("授权成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	/**
	 * 获取已授权资源树
	 * @return
	 */
	public void getAutorizationTree(){
		try {
			if(orgGUID==null||orgGUID.equals(""))throw new RuntimeException("组织机构ID为空,获取数据失败");
			//this.authorizeServiceImpl.getAutorization(orgGUID);
			String str=this.authorizeServiceImpl.getAutorization(orgGUID);
			str=str==null||str.trim().equals("")?"[]":str;
			this.clientProxy.getResponse().setContentType("text/text;charset=utf-8");
			PrintWriter pw=this.clientProxy.getResponse().getWriter();
			pw.print(str);
			pw.flush();
			pw.close();
			//System.err.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	/**
	 * 获取未未授权资源树
	 * @return
	 */
	public void getUnAutorizationTree(){
		try {
			if(orgGUID==null||orgGUID.equals(""))throw new RuntimeException("组织机构ID为空,获取数据失败");
			//this.authorizeServiceImpl.getAutorization(orgGUID);
			String str=this.authorizeServiceImpl.getUnAutorization(orgGUID);
			str=str==null||str.trim().equals("")?"[]":str;
			this.clientProxy.getResponse().setContentType("text/text;charset=utf-8");
			PrintWriter pw=this.clientProxy.getResponse().getWriter();
			pw.print(str);
			pw.flush();
			pw.close();	
			System.err.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/***
	 * 新增权限
	 * @return
	 */
	public String addAutorization(){
		try {
			if(orgGUID==null||orgGUID.equals(""))throw new RuntimeException("组织机构ID为空,获取数据失败");
			if(meIds==null||meIds.equals("")) throw new RuntimeException("授权失败,你没有选择功能·");
			this.authorizeServiceImpl.saveAutorization(orgGUID, meIds);
			super.smsg=new SimpleMsg("授权成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	/***
	 * 取消权限
	 * @return
	 */
	public String delAutorization(){
		try {
			if(orgGUID==null||orgGUID.equals(""))throw new RuntimeException("组织机构ID为空,获取数据失败");
			if(meIds==null||meIds.equals("")) throw new RuntimeException("取消权限失败,你没有选择功能·");
			this.authorizeServiceImpl.saveUnAutorization(orgGUID, meIds);
			super.smsg=new SimpleMsg("取消权限成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	
	
	
	
	
	
	
	/**
	 * @param orgGUID the orgGUID to set
	 */
	public void setOrgGUID(String orgGUID) {
		this.orgGUID = orgGUID;
	}
	/**
	 * @param meIds the meIds to set
	 */
	public void setMeIds(String meIds) {
		this.meIds = meIds;
	}







	public void setAuthorizeServiceImpl(AuthorizeService authorizeServiceImpl) {
		this.authorizeServiceImpl = authorizeServiceImpl;
	}

}
