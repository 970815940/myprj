/* SspWfCategoryAction.java 
 * Dec 18, 2013  11:28:47 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jbpm.graph.def.ProcessDefinition;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import com.sps.ps.commonbiz.security.action.SysUserLoginAction;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.finance.entity.FinanceTypepath;
import com.sps.ps.utils.StringUtil;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;
import com.sps.ps.utils.entity.Tree;
import com.sysps.ps.workflow.entity.SspWfCategory;
import com.sysps.ps.workflow.service.SspWfCategoryService;

/**
 *<p>SspWfCategoryAction</p>
 *<p></p> 
 *<p></p> 
 * @author  <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 18, 2013 11:28:47 AM
 * @version  
 */
public class SspWfCategoryAction extends SysWebCtrlAction {
	/** 
	 *serialVersionUID
	 */  
	
	private static final long serialVersionUID = 1L;
	private SspWfCategoryService categoryServiceImpl;
	private SspWfCategory cate;
	private int id;
	private String type;
	private String nodeName;
	private Properties proIcon=null;
	public static final String PROPERTIES_ICON="flowDefinitionIco.properties";
	/**
	 * 文件夹图标
	 */
	private String CATEGORYICON=null;
	/**
	 * 流程定义文件夹图标
	 */
	private String PROCESSCATEGORYICON=null;
	/**
	 * 实例定义图标
	 */
	private String PROCESSICON=null;
	public SspWfCategoryAction(){
			//File file=ResourceUtils.getFile("classpath:com/sysps/ps/workflow/action/"+PROPERTIES_ICON);
		try {
			Resource res =new ClassPathResource("com/sysps/ps/workflow/action/"+PROPERTIES_ICON);
			proIcon=PropertiesLoaderUtils.loadProperties(res);
			CATEGORYICON=proIcon.getProperty("categoryICON");
			PROCESSCATEGORYICON=proIcon.getProperty("ProcessCategoryICON");
			PROCESSICON =proIcon.getProperty("ProcessICON");		
			log.debug("initialized processDefinition icon success url["+res.getURI().toString()+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public String saveSspWfCategory(){
		try {
			if(cate.getId()==null){
				this.categoryServiceImpl.addSspWfCategory(cate);
			}else{
				this.categoryServiceImpl.updateSspWfCategory(cate);
			}
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	public String deleteSspWfCategory(){
		try {
			SspWfCategory wfcate=this.categoryServiceImpl.getSspWfCategoryById(id);
			this.categoryServiceImpl.deleteSspWfCategory(wfcate);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	public String getSspWfCategoryByList(){
		try {
			int count=this.categoryServiceImpl.countBySspWfCategory(null);
			List lst=this.categoryServiceImpl.getBySspWfCategory(null, start, this.limit);
			super.tmsg=new TableMsg(true,"",lst,count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}	
	public String getgetSspWfCategoryByListId(){
		try {
			SspWfCategory cate=this.categoryServiceImpl.getSspWfCategoryById(id);
			super.smsg=new SimpleMsg("",true,cate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public void getpdTree() throws UnsupportedEncodingException{

		try {
//			List<SspWfCategory> lst=this.categoryServiceImpl.getBySspWfCategory(null, -1, -1);			
//			Tree tree=new Tree();
//			tree.setObjForm(lst, "id", "parent");
//			String str=getInfo(tree, (id==0?-1:id)+"");
//			System.out.println(str+"====");
			if(cate==null)cate=new SspWfCategory();
			cate.setParent(Long.parseLong(id+""));
			List lst=null;
			if(type.equals("p")){
				String dpName=java.net.URLDecoder.decode(nodeName,"UTF-8");
				lst=this.categoryServiceImpl.getProcessDefinitionByName(dpName);
			}else{
				lst =this.categoryServiceImpl.getwfTree(cate);
			}
			
			StringBuffer str=new StringBuffer("[");
			for (int i = 0; i <lst.size(); i++) {
				if(lst.get(i) instanceof SspWfCategory ){
					SspWfCategory wf=(SspWfCategory)lst.get(i);
					if(lst.size()-1==i){
						str.append("{\"text\":\""+wf.getName()+"\",\"id\":\""+wf.getId()+"\",\"type\":\""+wf.getType()+"\",icon:\""+(wf.getType().equals("c")?this.clientProxy.getContextPath()+CATEGORYICON:this.clientProxy.getContextPath()+PROCESSCATEGORYICON)+"\"}");
					}else{
						str.append("{\"text\":\""+wf.getName()+"\",\"id\":\""+wf.getId()+"\",\"type\":\""+wf.getType()+"\",icon:\""+(wf.getType().equals("c")?this.clientProxy.getContextPath()+CATEGORYICON:this.clientProxy.getContextPath()+PROCESSCATEGORYICON)+"\"},");
					}					
				}else if(lst.get(i) instanceof ProcessDefinition){
					ProcessDefinition pd=(ProcessDefinition)lst.get(i);
					if(lst.size()-1==i){
						str.append("{\"text\":\""+pd.getName()+" _v"+pd.getVersion()+"\",\"id\":\""+"p_"+pd.getId()+"\",\"type\":\"pd\",leaf:true,icon:\""+this.clientProxy.getContextPath()+PROCESSICON+"\"}");
					}else{
						str.append("{\"text\":\""+pd.getName()+" _v"+pd.getVersion()+"\",\"id\":\""+"p_"+pd.getId()+"\",\"type\":\"pd\",leaf:true,icon:\""+this.clientProxy.getContextPath()+PROCESSICON+"\"},");
					}					
				}

			}
			str.append("]");
			System.out.println(str.toString());
			HttpServletResponse hr=clientProxy.getResponse();
			hr.setContentType("text/text;charset=utf-8");
			try {
				PrintWriter pw=hr.getWriter();
				pw.write(str.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getInfo(Tree tree,String pid){
		StringBuffer sb=new StringBuffer();
		List<SspWfCategory> lst=tree.getChildrens(pid);
		if(lst!=null&&lst.size()!=0){
			sb.append("[");
			for (int i = 0; i <lst.size(); i++) {
				
				SspWfCategory swc=lst.get(i);
				boolean leaf=tree.hasChildren(swc.getId()+"");
					sb.append("{");
					sb.append("\"text\":\""+swc.getName()+"\",");
					sb.append("\"id\":\""+swc.getId()+"\",");
					sb.append("\"leaf\":\""+(swc.getType().equals("p")?true:false)+"\"");
				
					if(leaf){
						sb.append(",\"children\":"+getInfo(tree, swc.getId()+""));
					}
				sb.append("}");
			}
			sb.append("]");
		}
		return sb.toString();
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param categoryServiceImpl the categoryServiceImpl to set
	 */
	public void setCategoryServiceImpl(SspWfCategoryService categoryServiceImpl) {
		this.categoryServiceImpl = categoryServiceImpl;
	}
	
	/**
	 * @return the cate
	 */
	public SspWfCategory getCate() {
		return cate;
	}
	/**
	 * @param cate the cate to set
	 */
	public void setCate(SspWfCategory cate) {
		this.cate = cate;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}	
	private static Logger log = Logger.getLogger(SspWfCategoryAction.class);
}
    
