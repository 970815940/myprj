package com.sps.ps.finance.action;

import java.util.Date;
import java.util.List;

import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.security.entity.LoginContext;
import com.sps.ps.commonbiz.security.entity.LoginUser;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.finance.entity.Finance;
import com.sps.ps.finance.service.FinanceService;
import com.sps.ps.utils.StringUtil;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class FinanceAction extends SysWebCtrlAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FinanceService financeServiceImpl;
	public void setFinanceServiceImpl(FinanceService financeServiceImpl) {
		this.financeServiceImpl = financeServiceImpl;
	}
	//fiedls
	private Finance fn;
	private String fnId;
	private String typPath;
	public void setTypPath(String typPath) {
		this.typPath = typPath;
	}
	public String saveFinance(){
		try {
			LoginUser lu=LoginContext.getLoginUser(this.clientProxy);
			if(fn==null)new RuntimeException("保存的对象不能为空");
			if(fn.getFId()==null||fn.getFId().equals("")){//INSERT
				fn.setFCreatetime(new Date());
				fn.setFId(GETKEY.getKey());
				fn.setFUserid(lu.getUserId());
				fn.setFUsername(lu.getUserName());
				this.financeServiceImpl.addFinance(fn);
			}else{
				Finance fn_=this.financeServiceImpl.getFinanceById(fn.getFId());
				fn_.setFApplytime(fn.getFApplytime());
				fn_.setFMoney(fn.getFMoney());
				fn_.setFReadme(fn.getFReadme());
				fn_.setFType(fn.getFType());
				fn_.setFTypepath(fn.getFTypepath());
				this.financeServiceImpl.updateFinance(fn_);
			}
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	public String deleteFinance(){
		try {
			if(!StringUtil.isEmpty(fnId))throw new RuntimeException("删除失败.删除对象的ID为空");
			Finance fn=this.financeServiceImpl.getFinanceById(fnId);
			if(fn==null)throw  new RuntimeException("对象不存在,请刷新后再试");
			this.financeServiceImpl.deleteFinance(fn);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	public String getFinanceById(){
		try {
			if(!StringUtil.isEmpty(fnId))throw new RuntimeException("删除失败.删除对象的ID为空");
			Finance fn=this.financeServiceImpl.getFinanceById(fnId);
			super.smsg=new SimpleMsg("获取对象成功",true,fn);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getFinanceByList(){
		try {
			LoginUser lu=LoginContext.getLoginUser(this.clientProxy);
			String[] colums=new String[]{"FUserid","FTypepath"};
			String[] ysf=new String[]{"=","like"};
			String[] values=new String[]{lu.getUserId(),typPath+"%"};
			int rows=this.financeServiceImpl.countFinance(colums,ysf,values);
			List lst=this.financeServiceImpl.getFinanceByList(colums,ysf,values, start, limit);
			super.tmsg=new TableMsg(true,"",lst,rows);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}


	public Finance getFn() {
		return fn;
	}
	public void setFn(Finance fn) {
		this.fn = fn;
	}
	public void setFnId(String fnId) {
		this.fnId = fnId;
	}

}

