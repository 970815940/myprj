package com.sps.ps.commonbiz.org.action;

import java.util.List;

import com.sps.ps.commonbiz.org.entity.SysUser;
import com.sps.ps.commonbiz.org.service.SysUserService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.Encryptor;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class SysUserAction  extends SysWebCtrlAction{
	private SysUserService sysUserServiceImpl;

	/**
	 * @param sysUserServiceImpl the sysUserServiceImpl to set
	 */
	public void setSysUserServiceImpl(SysUserService sysUserServiceImpl) {
		this.sysUserServiceImpl = sysUserServiceImpl;
	}
	
	private SysUser su;
	private String dptId;//部门ID
	private String userId;//用户ID；



	public String saveSysUser(){
		try {

			if(su.getGuid()!=null&&!su.getGuid().equals("")){
				//判断密码是否为MD5 如果不是，则加密，否则直接存入数据库
				if(su.getSuPassword().length()>17){//暂时以长度大于30作为MD5已加密过
				}else{
					su.setSuPassword(Encryptor.MD5String(su.getSuPassword()));
				}
				SysUser su_=this.sysUserServiceImpl.getSysUserById(su.getGuid());
				su_.setSuDisplayname(su.getSuDisplayname());
				su_.setSuDptid(su.getSuDptid());
				su_.setSuIndex(su.getSuIndex());
				su_.setSuStatus(su.getSuStatus());
				su_.setSuPassword(su.getSuPassword());
				this.sysUserServiceImpl.updateSysUser(su_);
			}else{
				su.setGuid(GETKEY.getKey("user"));
				//对密码进行MD5加密
				su.setSuPassword(Encryptor.MD5String(su.getSuPassword()));
				this.sysUserServiceImpl.addSysUser(su);
			}
			super.smsg=new SimpleMsg("保存成功",true,"");
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.AJAX_SUCCESS;
	}
	public String getSysUserById(){
		try {
			if(userId==null||userId.equals(""))throw new RuntimeException("获取的用户ID为空,导致查询失败");
			SysUser su=this.sysUserServiceImpl.getSysUserById(userId);
			if(su==null)throw new RuntimeException("查询的用户不存在,请刷新后再试");
			super.smsg=new SimpleMsg("",true,su);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getSysUserByList(){
		try {
			String[] column=null;
			String[] ysf=null;
			String[] values=null;
			if(dptId!=null&&!"".equals(dptId)){
				column=new String[]{"suDptid"};
				ysf=new String[]{"="};
				values=new String[]{dptId};
			}		
			int rowCount=this.sysUserServiceImpl.countSysUser(column,ysf, values);
			List lst=this.sysUserServiceImpl.getSysUserByList(column,ysf, values, super.start, super.limit);

			super.tmsg=new TableMsg(lst,rowCount);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	public String deleteSysUser(){
		try {
			if(userId==null||userId.equals(""))throw new RuntimeException("删除的用户ID为空,导致删除失败");
			SysUser su=this.sysUserServiceImpl.getSysUserById(userId);
			if(su==null) throw new RuntimeException("用户不存在,可能已经被删除,请删除列表再重试");
			this.sysUserServiceImpl.deleteSysUser(su);
			super.smsg=new SimpleMsg("删除成功",true,"");
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.DELETE_SUCCESS;
	}
	/**
	 * @return the su
	 */
	public SysUser getSu() {
		return su;
	}
	/**
	 * @param su the su to set
	 */
	public void setSu(SysUser su) {
		this.su = su;
	}	
	/**
	 * @param dptId the dptId to set
	 */
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}	
}
