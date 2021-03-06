package com.sps.ps.commonbiz.security.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.entity.SysUser;
import com.sps.ps.commonbiz.org.service.SysDepartmentService;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.commonbiz.org.service.SysStationService;
import com.sps.ps.commonbiz.org.service.SysUserService;
import com.sps.ps.commonbiz.pk.action.PrimarykeyAction;
import com.sps.ps.commonbiz.security.entity.LoginContext;
import com.sps.ps.commonbiz.security.entity.LoginUser;
import com.sps.ps.commonbiz.security.exception.SecurityException;
import com.sps.ps.commonbiz.security.service.AuthorizeService;
import com.sps.ps.commonbiz.security.service.IDChecking;
import com.sps.ps.commonbiz.security.service.impl.AuthorizeServiceImpl;
import com.sps.ps.core.web.ClientProxy;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.Encryptor;
import com.sps.ps.utils.entity.SimpleMsg;
/**
 * 用户登录
 * @author taoxs
 *
 */
public class SysUserLoginAction extends SysWebCtrlAction {
	private static Logger log = Logger.getLogger(SysUserLoginAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SysUserService sysUserServiceImpl;
	private SysOrgService sysOrgServiceImpl;
	private SysDepartmentService sysDepartmentServiceImpl;
	private SysStationService syStationServiceImpl;
	private AuthorizeService authorizeServiceImpl;
	private SysUser UserId;
	
	public IDChecking isChecking=new IDChecking(){

		public boolean isMD5(String loginId) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean validateUser(String loginid, String password) {
			if(loginid==null||"".equals(loginid)){
				log.debug("用户名为空---->验证失败");
				return false;
			}
			if(password==null||"".equals(password)){
				log.debug("密码为空--->验证失败");
				return false;
			}
			SysUser su=sysUserServiceImpl.getSysUserByLoginId(loginid);
			UserId=su;
			if(su==null){
				log.debug("用户名不存在--->验证失败");
				return false;
			}
			try {
				password=Encryptor.MD5String(password);
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("MD5加密密码失败");
				return false;
			}
			return su.getSuPassword().equals(password);
		}
		
	};
	private String username;
	private String password;
	
	/**
	 * 登录系统
	 * @return
	 */
	public String login(){
		boolean validate=false;
		try {
			log.debug(username+"尝试登录系统!");
			validate=isChecking.validateUser(username, password);
			if(validate){
				log.debug(username+"-->身份验证成功..正在跳转页面");
				//确定登录成功...下面开始读取当前登录人员信息
				LoginUser lu=getUserInfo();
				super.clientProxy.setAttribute(LoginContext.LOGIN_SESSION_KEY, lu,clientProxy.HTTP_SESSION);
				initSystem();
				return super.SUCCESS;
			}else{
				log.debug(username+"-->身份验证失败..请重新输入用户名或者密码");
				//super.smsg=new SimpleMsg("登录失败,用户名或者密码错误",false,null);
				this.clientProxy.getResponse().setContentType("text/html;charset=utf-8");
				this.clientProxy.getResponse().getWriter().print("<script>alert('身份验证失败..请重新输入用户名或者密码');history.back();</script>");
				this.clientProxy.getResponse().getWriter().flush();
				this.clientProxy.getResponse().getWriter().close();
			//	return super.STRING;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.clientProxy.getResponse().setContentType("text/html;charset=utf-8");
				this.clientProxy.getResponse().getWriter().print("<script>alert('"+e.getMessage()+"');history.back();</script>");
				this.clientProxy.getResponse().getWriter().flush();
				this.clientProxy.getResponse().getWriter().close();			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//super.smsg=new SimpleMsg("登录失败,"+e.getMessage(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	/***
	 * 获取用户的详细信息 
	 * @return
	 */
	private LoginUser getUserInfo(){
		LoginUser loginUser=new LoginUser();
		//首先根据用户表的主键ID 获取SYSORG的数据
		List lst=sysOrgServiceImpl.getSysOrgByList(new String[]{"soUser"}, new String[]{"="}, new String[]{UserId.getGuid()}, super.ALLPAGE, super.ALLPAGE);
		
		if(lst.size()==1){
			SysOrg so_=(SysOrg)lst.get(0);
			//机构,部门,岗位,人员===
			String[] path=so_.getSoOrgpath().split("/");
			loginUser.setGuid(so_.getSoGuid());
			for (int i = 0; i < path.length; i++) {
				SysOrg so=this.sysOrgServiceImpl.getSysOrgById(path[i]);
				if(so.getSoOrgkind().equals("org")){//机构
					loginUser.setOrgId(so.getSoGuid());
					loginUser.setOrgName(so.getSoOrgdispalyname());
				}else if(so.getSoOrgkind().equals("dpt")){//部门
					loginUser.setDptId(so.getSoDptid());
					loginUser.setDptName(so.getSoOrgdispalyname());
				}else if(so.getSoOrgkind().equals("ssn")){//岗位
					loginUser.setStationId(so.getSoStation());
					loginUser.setStationName(so.getSoOrgdispalyname());
				}else if(so.getSoOrgkind().equals("user")){//人员
					loginUser.setLoginUserId(UserId.getSuId());
					loginUser.setUserId(so.getSoUser());
					loginUser.setUserName(so.getSoOrgdispalyname());				
				}else {
					log.error("获取角色错误");
				}
			}
		}else if(lst.size()>1){//有多个角色存在
			log.debug("有多个角色存在-->你选择角色");
		}else{
			log.debug("角色获取异常：角色的个数="+lst.size());
		}
		log.debug("当前登录的角色为:"+loginUser.getOrgName()+"/"+loginUser.getDptName()+"/"+loginUser.getStationName()+"/"+loginUser.getUserName());
		return loginUser;
	}
	/**
	 * 登录成功后 进行初始化系统
	 */
	public void initSystem(){
		try {
			log.debug("正在初始化系统-------->");
			String skin="";
			String contextpath=super.clientProxy.getRequest().getContextPath();
			String skin_ext_css="default";
			String language="zh_CN";
			StringBuffer sb=new StringBuffer();
			sb.append("<link charset=\"utf-8\" href=\""+contextpath+"/jslib/resources/css/ext-"+skin_ext_css+"-all.css\" type=\"text/css\" rel=\"stylesheet\"/>");
			sb.append("<script type=\"text/javascript\" src=\""+contextpath+"/jslib/adapter/ext/ext-base.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\""+contextpath+"/jslib/ext-all.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\""+contextpath+"/jslib/lang/ext-lang-"+language+".js\"></script>");
			sb.append("<script type=\"text/javascript\">");
			sb.append("Ext.appRootPath='"+contextpath+"';");
			sb.append("Ext.BLANK_IMAGE_URL='"+contextpath+"/jslib/resources/images/default/s.gif';");
			sb.append("</script>");	
			LoginUser lu=LoginContext.getLoginUser(super.clientProxy);
			
			super.clientProxy.setAttribute("SSP_LOGINUSER",lu ,clientProxy.HTTP_SESSION);
			super.clientProxy.setAttribute("SSP_CONTENTHEADER", sb.toString(), ClientProxy.HTTP_SESSION);
			super.clientProxy.setAttribute("SSP_CONTEXTPATH", contextpath,ClientProxy.HTTP_SESSION);
			super.clientProxy.setAttribute("SSP_TITLE", "个人软件作品工作室",ClientProxy.HTTP_SESSION);
			//获取用户菜单
			//String str=authorizeServiceImpl.getAutorization(LoginContext.getLoginUser(clientProxy).getGuid());
			//super.clientProxy.setAttribute("menuStr",str,clientProxy.HTTP_SESSION);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("系统初始化失败");
		}
	}
	/***
	 * 注销
	 * @return
	 */
	public String exitUser(){
		try {
			LoginContext.removeLoginUser(this.clientProxy);
			log.warn("注销成功");
			super.smsg=new SimpleMsg("注销成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.toString(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	/**
	 * 修改密码
	 * @return
	 */
	public String modifyPassword(){
		try {
			LoginUser lu=LoginContext.getLoginUser(this.clientProxy);
			if(lu==null) throw new SecurityException("你没有登录");
			String password1=this.clientProxy.getPar("password1","");
			String password2=this.clientProxy.getPar("password2","");
			String password3=this.clientProxy.getPar("password3","");
			SysUser su=this.sysUserServiceImpl.getSysUserById(lu.getUserId());
			if(password1.equals("")){
				throw new SecurityException("旧密码不能为空");
			}
			if(!su.getSuPassword().equals(Encryptor.MD5String(password1))){
				throw new SecurityException("旧密码错误");
			}
			if(!password2.equals(password3)){
				throw new SecurityException("2次新密码输入不一致");
			}
			String md5Pas=Encryptor.MD5String(password2);
			su.setSuPassword(md5Pas);
			this.sysUserServiceImpl.updateSysUser(su);
			super.smsg=new SimpleMsg("密码修改成功",true,null);
			log.debug("密码修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.toString(),false,null);
			
		}
		
		return super.AJAX_SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param sysUserServiceImpl the sysUserServiceImpl to set
	 */
	public void setSysUserServiceImpl(SysUserService sysUserServiceImpl) {
		this.sysUserServiceImpl = sysUserServiceImpl;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSysOrgServiceImpl(SysOrgService sysOrgServiceImpl) {
		this.sysOrgServiceImpl = sysOrgServiceImpl;
	}
	public void setSysDepartmentServiceImpl(
			SysDepartmentService sysDepartmentServiceImpl) {
		this.sysDepartmentServiceImpl = sysDepartmentServiceImpl;
	}
	public void setSyStationServiceImpl(SysStationService syStationServiceImpl) {
		this.syStationServiceImpl = syStationServiceImpl;
	}
	public void setAuthorizeServiceImpl(AuthorizeService authorizeServiceImpl) {
		this.authorizeServiceImpl = authorizeServiceImpl;
	}
}
