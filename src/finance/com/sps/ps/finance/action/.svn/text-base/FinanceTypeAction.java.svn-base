package com.sps.ps.finance.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.finance.entity.FinanceTypepath;
import com.sps.ps.finance.service.FinanceTypeService;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;
/***
 * 
 * @author 970815940@qq.com
 * 
 */
public class FinanceTypeAction extends SysWebCtrlAction {
	private FinanceTypeService financeTypeServiceImpl;
	private static Logger log = Logger.getLogger(FinanceTypeAction.class);
	//fields//
	private FinanceTypepath ftp;
	private String ftpId;
	private String pid="-1";
	

	public void setFtp(FinanceTypepath ftp) {
		this.ftp = ftp;
	}
	public void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}
	public void getFinanceTree(){
		try {
			String str=this.financeTypeServiceImpl.getFinanceTypeTree(pid);
			this.clientProxy.getResponse().setContentType("text/text;charset=utf-8");
			PrintWriter pw=this.clientProxy.getResponse().getWriter();
			log.warn(str);
			pw.print(str);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 保存 --->保存新增  修改
	 * @return
	 */
	public String saveFinanceType(){
		try {
			if(ftp==null)throw new RuntimeException("保存的对象不能为空");
			if(ftp.getFtId()==null||ftp.getFtId().equals("")){
				ftp.setFtId(GETKEY.getKey("financeType"));
				this.financeTypeServiceImpl.saveFinanceType(ftp);
			}else{
				this.financeTypeServiceImpl.updateFinanceType(ftp);
			}
			log.debug("保存成功");
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	public String deleteFinanceType(){
		try {
			if(ftpId==null||ftpId.equals(""))throw new RuntimeException("删除失败,id为空");
			ftp=this.financeTypeServiceImpl.getFinanceTypeById(ftpId);
			if(ftp==null)throw new RuntimeException("对象不存在.请刷新后再试");
			this.financeTypeServiceImpl.deleteFinanceType(ftp);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	public String getFinanceTypeById(){
		try {
			if(ftpId==null||ftpId.equals(""))throw new RuntimeException("获取对象失败,id为空");
			ftp=this.financeTypeServiceImpl.getFinanceTypeById(ftpId);			
			super.smsg=new SimpleMsg("获取成功",true,ftp);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getFinanceTypeByList(){
		try {
			int rows=this.financeTypeServiceImpl.countFinanceType(new String[]{}, new String[]{}, new String[]{});
			List lst=this.financeTypeServiceImpl.getFinanceTypeByList(new String[]{}, new String[]{}, new String[]{}, start, super.limit);
			super.tmsg=new TableMsg(true,"",lst,rows);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);
			
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	
	public void setFinanceTypeServiceImpl(FinanceTypeService financeTypeServiceImpl) {
		this.financeTypeServiceImpl = financeTypeServiceImpl;
	}
	public FinanceTypepath getFtp() {
		return ftp;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
