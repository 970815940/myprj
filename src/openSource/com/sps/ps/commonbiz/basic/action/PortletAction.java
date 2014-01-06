package com.sps.ps.commonbiz.basic.action;

import java.util.List;

import com.sps.ps.commonbiz.basic.entity.Portlet;
import com.sps.ps.commonbiz.basic.exceptions.PortletException;
import com.sps.ps.commonbiz.basic.service.PortletService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.StringUtil;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

/**
 * @author 970815940@qq.com
 *
 */
public class PortletAction extends SysWebCtrlAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PortletService portletServiceImpl;
	public void setPortletServiceImpl(PortletService portletServiceImpl) {
		this.portletServiceImpl = portletServiceImpl;
	}
	//fields    start
	private Portlet pr;
	private String prId;

	//fields     end
	public String savePortlet(){
		try {
			if(pr==null)throw new PortletException("保存的对象不能为空");
			if(StringUtil.isEmpty(pr.getId())){
				this.portletServiceImpl.updatePortlet(pr);//update
			}else{
				pr.setId(GETKEY.getKey());
				this.portletServiceImpl.savePortlet(pr);
			}
			
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	public String deletePortlet(){
		try {
			if(StringUtil.isEmpty(this.prId)==false)new PortletException("删除失败,ID为空");
			pr=this.portletServiceImpl.getPortletById(prId);
			if(pr==null)new PortletException("删除的对象不存在,请刷新后再说");
			this.portletServiceImpl.deletePortlet(pr);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	public String getPortletById(){
		try {
			if(StringUtil.isEmpty(this.prId)==false)new PortletException("获取对象失败,ID为空");
			pr=this.portletServiceImpl.getPortletById(prId);
			super.smsg=new SimpleMsg("获取对象成功",true,pr);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);			
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getPortletByList(){
		try {
			int rows=this.portletServiceImpl.countByPortlet(new String[]{});
			List lst=this.portletServiceImpl.getPortletByList(new String[]{}, new String[]{}, new String[]{}, start, limit);
			super.tmsg=new TableMsg(true,"获取数据成功",lst,rows);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);				
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}

	
	
	
	//fields  get set
	public Portlet getPr() {
		return pr;
	}
	public void setPr(Portlet pr) {
		this.pr = pr;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}	
}
