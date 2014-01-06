package com.sps.ps.commonbiz.org.action;

import java.util.List;

import com.sps.ps.commonbiz.org.entity.SysStation;
import com.sps.ps.commonbiz.org.service.SysStationService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class SysStationAction extends SysWebCtrlAction {
	private SysStationService sysStationServiceImpl;
	private SysStation ss;
	private String ssid;
	


	/**
	 * @param sysStationServiceImpl the sysStationServiceImpl to set
	 */
	public void setSysStationServiceImpl(SysStationService sysStationServiceImpl) {
		this.sysStationServiceImpl = sysStationServiceImpl;
	}
	/**
	 * 保存岗位对象
	 * @return
	 */
	public String saveSysStation(){
		try {
			if(ss==null)throw new RuntimeException("保存的岗位对象为空,保存失败");
			if(ss.getGuid()==null||ss.getGuid().equals("")){
				ss.setGuid(GETKEY.getKey("station"));
				ss.setSsState("0");
				this.sysStationServiceImpl.addSysStation(ss);
			}else{
				this.sysStationServiceImpl.updateSysStation(ss);
			}
			super.smsg=new SimpleMsg("保存成功",true,ss);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	public String updateSysStation(){
		try {
			if(ss==null)throw new RuntimeException("修改的对象为空,修改失败");
			SysStation ss_=this.sysStationServiceImpl.getSysStationById(ss.getGuid());
			if(ss_==null)throw new RuntimeException("修改的对象不存在,请刷新后再试");
			ss_.setSsDemo(ss.getSsDemo());
			ss_.setSsDispalyname(ss.getSsDispalyname());
			ss_.setSsIndex(ss.getSsIndex());
			ss_.setSsState(ss.getSsState());
			this.sysStationServiceImpl.updateSysStation(ss_);
			super.smsg=new SimpleMsg("修改成功",true,ss_);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.UPDATE_SUCCESS;
	}
	public String delSysStation(){
		try {
			if(ssid==null||ssid.equals(""))throw new RuntimeException("删除的ID为空");
			SysStation ss_=this.sysStationServiceImpl.getSysStationById(ssid);
			if(ss_==null)throw new RuntimeException("删除的对象不存在,请刷新后再试");
			this.sysStationServiceImpl.deleteSysStation(ss_);
			super.smsg=new SimpleMsg("删除成功",true,ss_.getGuid());
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	public String getSysStationById(){
		try {
			if(ssid==null||ssid.equals(""))throw new RuntimeException("查询的ID为空");
			SysStation ss_=this.sysStationServiceImpl.getSysStationById(ssid);
			if(ss_==null)throw new RuntimeException("查询的对象不存在,程序异常");
			super.smsg=new SimpleMsg("查询成功",true,ss_);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getSysStationByList(){
		try {
			int rows=this.sysStationServiceImpl.countSysStation(new String[]{}, new String[]{},new String[]{});
			List lst=this.sysStationServiceImpl.getSysStationByList(new String[]{}, new String[]{},new String[]{}, start, limit);
			super.tmsg=new TableMsg(true,"",lst,rows);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(false,"获取数据失败",null,0);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	/**
	 * @param ss the ss to set
	 */
	public void setSs(SysStation ss) {
		this.ss = ss;
	}
	/**
	 * @param ssid the ssid to set
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	/**
	 * @return the ss
	 */
	public SysStation getSs() {
		return ss;
	}	
}
