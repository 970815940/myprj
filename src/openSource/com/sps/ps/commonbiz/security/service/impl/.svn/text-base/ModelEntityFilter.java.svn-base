package com.sps.ps.commonbiz.security.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.sps.ps.commonbiz.security.entity.LoginContext;
import com.sps.ps.commonbiz.security.entity.LoginUser;
import com.sps.ps.commonbiz.security.service.ModelRightService;
import com.sps.ps.core.web.ClientProxy;
import com.sps.ps.core.web.ModelRightFilter;
import com.sps.ps.utils.StringUtil;


/***
 * 用户访问模块 控制是否取得权限
 * @author 970815940@qq.com
 *
 */
public class ModelEntityFilter implements ModelRightFilter {
	private ModelRightService modelRightServiceImpl;
	private final static Log log = LogFactory.getLog(ModelEntityFilter.class);	
	
	public void setModelRightServiceImpl(ModelRightService modelRightServiceImpl) {
		this.modelRightServiceImpl = modelRightServiceImpl;
	}


	public boolean doFilter(ClientProxy cp) {
		String meId=cp.getPar("SSP.Authorzation");//试图访问的权限模块
		if(!StringUtil.isEmpty(meId)){
			return true;
		}
		LoginUser lu=LoginContext.getLoginUser(cp);
		String guid=lu.getGuid();
		List lst=this.modelRightServiceImpl.getModelRightByList(new String[]{"mrOrgguid","mrMeid"}, new String[]{"=","="}, new String[]{guid,meId}, -1, -1);
		if(lst==null||lst.size()==0){
			log.warn(lu.getGuid()+"~"+lu.getUserName()+"试图访问"+meId+"模块权限不足");
			return false;
		}
		return true;
	}




}
