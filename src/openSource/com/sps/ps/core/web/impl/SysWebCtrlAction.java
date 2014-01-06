/*
 * SysWebCtrlAction.java 1.0
 * 2013-8-17 22:40
 */
package com.sps.ps.core.web.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.sps.ps.commonbiz.security.entity.ModelRight;
import com.sps.ps.commonbiz.security.service.impl.ModelEntityFilter;
import com.sps.ps.core.bean.BeanContextHolders;
import com.sps.ps.core.web.ClientProxy;
import com.sps.ps.core.web.ModelRightFilter;
import com.sps.ps.core.web.SysWebCtrl;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;


/**
 * web控制中心
 * <b>这是与前后台交互控制类</b>
 * 2013-09-20 17:11:01 新增权限过滤器
 * @author taoxs
 * @since  1.0
 */
public class SysWebCtrlAction extends ActionSupport implements SysWebCtrl,Preparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SysWebCtrlAction.class);
	private ModelRightFilter mef;//权限过滤器
//返回前台 工具  start
	public SimpleMsg smsg;
	public TableMsg tmsg;
	public String strmsg;//返回普通字符串
	public List slist;//简单列表·
//返回前台工具end
	protected ClientProxy clientProxy;
	//分页属性开始
	public int start=0;//默认第0页
	public int limit=20;//默认每页20行数据
	//分页属性结束

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		doFilter();
		return super.execute();
	}
	public void doFilter() {
		log.debug("过滤器执行成功");
	}
	protected void cleanSmsg(){
		smsg=null;
	}
	protected void cleanTmsg(){
		tmsg=null;
	}
	public void prepare() throws Exception {
		
		try {
			cleanSmsg();
			cleanTmsg();
			strmsg=null;
			slist=null;
			clientProxy=new ClientProxyImpl(
					ServletActionContext.getRequest(),
					ServletActionContext.getResponse(),
					ServletActionContext.getRequest().getSession(),
					ServletActionContext.getServletContext()
			);
			if(!doMRFilter())throw new RuntimeException("权限不足");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean doMRFilter() {
		mef=(ModelEntityFilter)BeanContextHolders.getBean("ModelEntityFilter");
		//ModelEntityFilter.class
		return mef.doFilter(clientProxy);
	}	
	/**
	 * @return the smsg
	 */
	public SimpleMsg getSmsg() {
		return smsg;
	}
	/**
	 * @param smsg the smsg to set
	 */
	public void setSmsg(SimpleMsg smsg) {
		this.smsg = smsg;
	}
	/**
	 * @return the tmsg
	 */
	public TableMsg getTmsg() {
		return tmsg;
	}
	/**
	 * @param tmsg the tmsg to set
	 */
	public void setTmsg(TableMsg tmsg) {
		this.tmsg = tmsg;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the slist
	 */
	public List getSlist() {
		return slist;
	}
	/**
	 * @param slist the slist to set
	 */
	public void setSlist(List slist) {
		this.slist = slist;
	}


}
