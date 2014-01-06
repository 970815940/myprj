package com.sps.ps.commonbiz.org.action;

import java.io.PrintWriter;
import java.util.List;

import com.sps.ps.commonbiz.org.entity.SysDepartment;
import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.service.SysDepartmentService;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;
import com.sps.ps.utils.entity.Tree;

public class SysDepartmentAction  extends SysWebCtrlAction{
	private SysDepartmentService sysDepartmentServiceImpl;
	private SysDepartment sd;
	private String rootTreeId;
	private String nodeId;//用于删除



	public String saveSysDepartment(){
		try {
			if(sd.getGuid()!=null&&!sd.getGuid().equals("")){
				this.sysDepartmentServiceImpl.updateSysDepartment(sd);
			}else{
				sd.setGuid(GETKEY.getKey("dpt"));
				this.sysDepartmentServiceImpl.addSysDepartment(sd);
			}
			super.smsg=new SimpleMsg("保存成功",true,"");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.AJAX_SUCCESS;
	}
	public String getSysDepartmentById(){
		try {
			String id="";
			SysDepartment su=this.sysDepartmentServiceImpl.getSysDepartmentById(id);
			super.smsg=new SimpleMsg("",true,su);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	public String getSysDepartmentByList(){
		try {
			int rowCount=this.sysDepartmentServiceImpl.countSysDepartment("", new String[]{});
			List lst=this.sysDepartmentServiceImpl.getSysDepartmentByList("", new String[]{}, 1, 2);
			super.tmsg=new TableMsg(lst,rowCount);
		} catch (Exception e) {
			e.printStackTrace();
			super.tmsg=new TableMsg(e.getMessage(),false);
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	public String deleteSysDepartment(){
		try {
			SysDepartment su=this.sysDepartmentServiceImpl.getSysDepartmentById(nodeId);
			this.sysDepartmentServiceImpl.deleteSysDepartment(su);
			super.smsg=new SimpleMsg("删除成功",true,"");
		} catch (Exception e) {
			e.printStackTrace();
			this.clientProxy.getResponse().setStatus(600);//随便更改个状态码，因为这个错误的信息。不能进success
			super.smsg=new SimpleMsg(e.getMessage(),false,"");
		}
		return super.DELETE_SUCCESS;
	}
	/**
	 * 获取机构树
	 */
	public void getDptTree(){
		try {
			List lst=this.sysDepartmentServiceImpl.getSysDepartmentByList("from SysDepartment", new String[]{}, -1, -1);
			Tree tree=new Tree();
			tree.setObjForm(lst, "guid", "sdPanentid");
			String str=getInfo(tree,rootTreeId==null||rootTreeId.equals("")?"-1":rootTreeId);
			this.clientProxy.getResponse().setContentType("text/text;charset=utf-8");
			PrintWriter pw=this.clientProxy.getResponse().getWriter();
			pw.print(str);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private String getInfo(Tree tree,String id){
		StringBuffer sb=new StringBuffer();
		
		List lst=tree.getChildrens(id);
		if(lst!=null&&lst.size()>0){
			sb.append("[");
			for (int i = 0; i <lst.size(); i++) {
				SysDepartment sdpt=(SysDepartment) lst.get(i);
				boolean leaf=tree.hasChildren(sdpt.getGuid());
				sb.append("{");
				sb.append("id:\""+sdpt.getGuid()+"\",");
				sb.append("text:\""+sdpt.getSdDisplayname()+"\",");
				sb.append("leaf:"+!leaf+",");
				sb.append("sdKind:\""+sdpt.getSdKind()+"\",");
				sb.append("sdIndex:\""+sdpt.getSdIndex()+"\",");
				sb.append("sdIstrue:\""+sdpt.getSdIstrue()+"\",");
				if(sdpt.getSdKind().equals("2")){
					sb.append("icon:'"+this.clientProxy.getRequest().getContextPath()+"/skins/default/org/dpt.gif'");
				}else{
					sb.append("icon:'"+this.clientProxy.getRequest().getContextPath()+"/skins/default/org/org.gif'");
				}
				if(leaf){
					sb.append(",children:"+getInfo(tree, sdpt.getGuid()));
				}
				sb.append("}");
				if(lst.size()-1!=i){
					sb.append(",");
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	/**
	 * @return the sd
	 */
	public SysDepartment getSd() {
		return sd;
	}
	/**
	 * @param sd the sd to set
	 */
	public void setSd(SysDepartment sd) {
		this.sd = sd;
	}
	/**
	 * @param sysDepartmentServiceImpl the sysDepartmentServiceImpl to set
	 */
	public void setSysDepartmentServiceImpl(
			SysDepartmentService sysDepartmentServiceImpl) {
		this.sysDepartmentServiceImpl = sysDepartmentServiceImpl;
	}
	/**
	 * @param rootTreeId the rootTreeId to set
	 */
	public void setRootTreeId(String rootTreeId) {
		this.rootTreeId = rootTreeId;
	}
	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
