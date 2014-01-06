package com.sps.ps.commonbiz.org.action;

import java.util.ArrayList;
import java.util.List;

import com.ibm.db2.jcc.b.w;
import com.sps.ps.commonbiz.org.entity.OrgExt;
import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.entity.SysUser;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class SysOrgAction  extends SysWebCtrlAction{
	private SysOrgService sysOrgServiceImpl;
	private String porgid;//父节点ID

    private String dptId;//部门ID
    private String ssId;//岗位ID
    private  String userIds;//用户ID 需要用,分割
	public String saveSysOrg(){
		try {
			SysOrg su=new SysOrg();
			if(su.getSoGuid()!=null){
				this.sysOrgServiceImpl.updateSysOrg(su);
			}else{
				this.sysOrgServiceImpl.addSysOrg(su);
			}
			super.smsg=new SimpleMsg("保存成功",true,"");
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.AJAX_SUCCESS;
	}
	public String getSysOrgById(){
		try {
			String id="DPT_20130055";
			SysOrg su=this.sysOrgServiceImpl.getSysOrgById(id);
			System.out.println(su);
			super.smsg=new SimpleMsg("",true,su);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getSysOrgByList(){
		try {
			int rowCount=this.sysOrgServiceImpl.countSysOrg(new String[]{},new String[]{}, new String[]{});
			List lst=this.sysOrgServiceImpl.getSysOrgByList(new String[]{},new String[]{}, new String[]{}, 1, 2);
			super.tmsg=new TableMsg(lst,rowCount);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	public String deleteSysOrg(){
		try {
			String id="";
			SysOrg su=this.sysOrgServiceImpl.getSysOrgById(id);
			this.sysOrgServiceImpl.deleteSysOrg(su);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),true,"");
		}
		return super.DELETE_SUCCESS;
	}
	/**
	 * 分配岗位
	 * @return
	 */
	public String assignStation(){
		try {
			this.sysOrgServiceImpl.assignStation(dptId, ssId);
			super.smsg=new SimpleMsg("岗位分配成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.clientProxy.getResponse().setStatus(600);
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	/***
	 * 分配人员
	 * @return
	 */
	public String assignUser(){
		try {
			this.sysOrgServiceImpl.assignUser(userIds, ssId);
			super.smsg=new SimpleMsg("人员分配成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			this.clientProxy.getResponse().setStatus(600);
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}		
		return super.AJAX_SUCCESS;
	}
	/**
	 * 异步方式获取ORG机构树节点
	 * @return
	 */
	public  String getSynchronizedOrgTree(){
		try {
			String[] column=new String[]{"soParentid"};
			String[] ysf=new String[]{"="};
			String[] values=new String[]{this.porgid==null||"".equals(porgid)?"-1":porgid};
			List lst=this.sysOrgServiceImpl.getSysOrgByList(column, ysf, values, -1, -1);
			List<OrgExt> oelst=new ArrayList<OrgExt>();
			String Icon=this.clientProxy.getRequest().getContextPath()+"/skins/default/org/";
			for (int i = 0; i < lst.size(); i++) {
				SysOrg so_=(SysOrg) lst.get(i);
				String iconGif=null;
				if(so_.getSoOrgkind().equals("org")){
					iconGif="org.gif";
				}else if(so_.getSoOrgkind().equals("dpt")){
					iconGif="dpt.gif";
				}else if(so_.getSoOrgkind().equals("ssn")){
					iconGif="ssn.gif";
				}else if(so_.getSoOrgkind().equals("user")){
					iconGif="user.gif";
					
				}
				OrgExt oe=new OrgExt(so_.getSoGuid(),so_.getSoOrgdispalyname(),so_,Icon+iconGif);
				if(so_.getSoOrgkind().equals("user"))oe.setLeaf(true);
			//	System.out.println(so_.getSoOrgdispalyname());
				oelst.add(oe);
			}
			
			super.slist=oelst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.SLIST;
	}
	
	/**
	 * @param sysOrgServiceImpl the sysOrgServiceImpl to set
	 */
	public void setSysOrgServiceImpl(SysOrgService sysOrgServiceImpl) {
		this.sysOrgServiceImpl = sysOrgServiceImpl;
	}
	/**
	 * @param porgid the porgid to set
	 */
	public void setPorgid(String porgid) {
		this.porgid = porgid;
	}
	/**
	 * @param dptId the dptId to set
	 */
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	/**
	 * @param ssId the ssId to set
	 */
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}	
}
