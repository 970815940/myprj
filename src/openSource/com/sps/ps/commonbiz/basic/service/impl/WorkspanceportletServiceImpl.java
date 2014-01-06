package com.sps.ps.commonbiz.basic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi2.hssf.record.formula.functions.Degrees;

import com.sps.ps.commonbiz.basic.dao.WorkspanceporletIDAO;
import com.sps.ps.commonbiz.basic.entity.Portlet;
import com.sps.ps.commonbiz.basic.entity.Workspanceporlet;
import com.sps.ps.commonbiz.basic.entity.wsPortlet;
import com.sps.ps.commonbiz.basic.exceptions.PortletException;
import com.sps.ps.commonbiz.basic.service.PortletService;
import com.sps.ps.commonbiz.basic.service.WorkspanceporletService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.security.entity.LoginContext;
import com.sps.ps.commonbiz.security.entity.LoginUser;
import com.sps.ps.core.web.ClientProxy;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.BuildHql;

public class WorkspanceportletServiceImpl implements WorkspanceporletService {
	private static Logger log = Logger.getLogger(WorkspanceportletServiceImpl.class);
	private WorkspanceporletIDAO workspanceporletDAOImpl;
	private PortletService portletServiceImpl;


	public int countByWorkspanceporlet(String[] values) {
		StringBuffer hql=new StringBuffer("select count(*) from Workspanceporlet where 1=1");
		//hql.append(BuildHql.createHql( ysf));
		return this.workspanceporletDAOImpl.countByWorkspanceporlet(hql.toString(), values);
	}

	public void deleteWorkspanceporlet(Workspanceporlet wspor) {
		this.workspanceporletDAOImpl.deleteWorkspanceporlet(wspor);

	}

	public Workspanceporlet getWorkspanceporletById(String id) {

		return this.workspanceporletDAOImpl.getWorkspanceporletById(id);
	}

	public List getWorkspanceporletByList(String[] columns, String[] ysf,
			String[] value, int start, int limit) {
		StringBuffer hql=new StringBuffer("from Workspanceporlet where 1=1");
		hql.append(BuildHql.createHql(columns, ysf));
		hql.append(" order by index");
		return this.workspanceporletDAOImpl.getWorkspanceporletByList(hql.toString(), value, start, limit);
	}

	public void saveWorkspanceporlet(Workspanceporlet wspor) {
		this.workspanceporletDAOImpl.saveWorkspanceporlet(wspor);
	}

	public void updateWorkspanceporlet(Workspanceporlet wspor) {
		this.workspanceporletDAOImpl.updateWorkspanceporlet(wspor);

	}

	public void setWorkspanceporletDAOImpl(
			WorkspanceporletIDAO workspanceporletDAOImpl) {
		this.workspanceporletDAOImpl = workspanceporletDAOImpl;
	}

	public List getUserForPortlet(String UserId) {
		StringBuffer hql=new StringBuffer("from Workspanceporlet where 1=1");
		hql.append(" and userid=?");
		hql.append(" order by index asc");
		List lst=this.workspanceporletDAOImpl.getWorkspanceporletByList(hql.toString(), new String[]{UserId}, -1, -1);
		if(lst==null||lst.size()==0){//如果用户的组件为空 则加载必选组件 并把必选组件默认添加到工作区
			log.debug("检测到"+UserId+"没有定义组件 系统默认添加必须组件到工作区");
				List lstPortlet=this.portletServiceImpl.getPortletByList(new String[]{"optional"}, new String[]{"="}, new String[]{"1"}, -1,-1);
				if(lstPortlet==null||lstPortlet.size()==0)throw new PortletException("程序没有设置必选组件!请联系管理员");
				for (int i = 0; i < lstPortlet.size(); i++) {
					Portlet por=(Portlet)lstPortlet.get(i);
					Workspanceporlet ws=new Workspanceporlet();
					ws.setId(GETKEY.getKey());
					ws.setIndex(i);
					ws.setPortletid(por);
					ws.setPosition("l");//默认在左边
					ws.setUserid(UserId);
					this.workspanceporletDAOImpl.saveWorkspanceporlet(ws);
				}
			//把必选组件默认添加到工作区 完成后 重新加载组件
			getUserForPortlet(UserId);
		}
		
		return lst==null||lst.size()==0?this.workspanceporletDAOImpl.getWorkspanceporletByList(hql.toString(), new String[]{UserId}, -1, -1):lst;
	}

	public List getPortletSetList(ClientProxy cp) {
		List list=portletServiceImpl.getPortletByList(null,null, null, -1, -1);
		LoginUser lu=LoginContext.getLoginUser(cp);
		List wsList=this.getWorkspanceporletByList(new String[]{"userid"},new String[]{"="}, new String[]{lu.getGuid()}, -1, -1);
		List lst=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			wsPortlet wp=new wsPortlet();			
			Portlet por=(Portlet)list.get(i);
			wp.setPortlet(por);
			int b=-1;
			for (int j = 0; j < wsList.size(); j++) {
				Workspanceporlet ws=(Workspanceporlet)wsList.get(j);
				if(por.getId().equals(ws.getPortletid().getId())){
					b=1;
					break;
				}
			}
			if(b==-1){
				wp.setIstrue("0");//表示未被添加
			}else{
				wp.setIstrue("1");//表示已经被添加
			}
			lst.add(wp);
		}
		return lst;
	}
	public void setPortletServiceImpl(PortletService portletServiceImpl) {
		this.portletServiceImpl = portletServiceImpl;
	}	

}
