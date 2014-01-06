package com.sps.ps.commonbiz.basic.action;

import java.util.List;

import com.sps.ps.commonbiz.basic.entity.Portlet;
import com.sps.ps.commonbiz.basic.entity.Workspanceporlet;
import com.sps.ps.commonbiz.basic.exceptions.PortletException;
import com.sps.ps.commonbiz.basic.service.WorkspanceporletService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.security.entity.LoginContext;
import com.sps.ps.commonbiz.security.entity.LoginUser;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.StringUtil;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class WorkspanceporletAction extends SysWebCtrlAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorkspanceporletService workspanceporletServiceImpl;
	
	public void setWorkspanceporletServiceImpl(
			WorkspanceporletService workspanceporletServiceImpl) {
		this.workspanceporletServiceImpl = workspanceporletServiceImpl;
	}
	//fields start
	private Workspanceporlet wsp;
	private String wspId;
	private String UserId;
	//fields end
	

	public String saveWorkspanceporlet(){
		try {
			if(wsp==null)throw new PortletException("保存失败,对象为空");
			if(StringUtil.isEmpty(wsp.getId())){
				Workspanceporlet wsp_=this.workspanceporletServiceImpl.getWorkspanceporletById(wsp.getId());
				wsp_.setIndex(wsp.getIndex());
				wsp_.setPosition(wsp.getPosition());
				this.workspanceporletServiceImpl.updateWorkspanceporlet(wsp_);
			}else{
				wsp.setId(GETKEY.getKey());
				wsp.setIndex(0);
				wsp.setPosition("l");//默认在最左边
				wsp.setUserid(LoginContext.getLoginUser(this.clientProxy).getGuid());
				this.workspanceporletServiceImpl.saveWorkspanceporlet(wsp);
			}
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	public String deleteWorkspanceporlet(){
		try {
			if(StringUtil.isEmpty(this.wspId)==false)throw new PortletException("删除失败，ID为空");
			wsp=this.workspanceporletServiceImpl.getWorkspanceporletById(this.wspId);
			if(wsp==null)throw new PortletException("对象不存在,或者已经被删除,请刷新后再试");
			if(wsp.getPortletid().getOptional().equals("1")){//必选组件不可删除
				throw new PortletException("["+wsp.getPortletid().getTitle()+"]是必须组件不能删除");
			}
			this.workspanceporletServiceImpl.deleteWorkspanceporlet(wsp);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.toString(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	public String getWorkspanceporletById(){
		try {
			if(StringUtil.isEmpty(this.wspId))throw new PortletException("获取对象失败，ID为空");
			wsp=this.workspanceporletServiceImpl.getWorkspanceporletById(this.wspId);			
			super.smsg=new SimpleMsg("获取对象成功",true,wsp);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getWorkspanceporletByList(){
		try {
			int rows=this.workspanceporletServiceImpl.countByWorkspanceporlet(new String[]{});
			List lst=this.workspanceporletServiceImpl.getWorkspanceporletByList(new String[]{}, new String[]{}, new String[]{}, start, limit);
			super.tmsg=new TableMsg(true,"获取列表成功",lst,rows);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	/**
	 * 根据用户全局ID 获取用户自定义的组件
	 * @return
	 */
	public String getUserForPorlet(){
		try {
			if(StringUtil.isEmpty(UserId)==false)throw new PortletException("根据用户提供的用户错误，用ID不能为空.组件加载失败");
			List lst=this.workspanceporletServiceImpl.getUserForPortlet(UserId);
			super.tmsg=new TableMsg(true,"",lst,0);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(false,e.toString(),null,0);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	/***
	 * 得到用户设置组件的列表 包含组件是否可用字段 1表示已经添加到用户工作空间 0表示未添加到工作空间
	 * @return
	 */
	public String getPortletSetList(){
		List list=this.workspanceporletServiceImpl.getPortletSetList(this.clientProxy);
		super.tmsg=new TableMsg(true,"",list,list.size());
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	
	
	public Workspanceporlet getWsp() {
		return wsp;
	}
	public void setWsp(Workspanceporlet wsp) {
		this.wsp = wsp;
	}
	public void setWspId(String wspId) {
		this.wspId = wspId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}	

}
