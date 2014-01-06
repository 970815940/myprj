package com.sps.ps.commonbiz.security.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.security.entity.ModelStruct;
import com.sps.ps.commonbiz.security.service.ModelStructService;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.Tree;

public class ModelStructAction extends SysWebCtrlAction {
	private ModelStructService modelStructServiceImpl;
	private static Logger log = Logger.getLogger(ModelStructAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelStruct ms;
	private String msId;

	/**
	 * 保存
	 * @return
	 */
	public String saveModelStruct(){
		try {
			if(ms==null)throw new RuntimeException("保存失败,保存的结构对象为空");
			if(ms.getMsId()==null||ms.getMsId().equals("")){
				ms.setMsId(GETKEY.getKey("model-struct"));
				this.modelStructServiceImpl.addModelStruct(ms);
				log.debug("新增成功---");
			}else{
				this.modelStructServiceImpl.updateModelStruct(ms);
				log.debug("修改成功----------------");
			}
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.AJAX_SUCCESS;
	}
	/**
	 * 删除
	 * @return
	 */
	public String deleteModelStruct(){
		try {
			if(msId==null||msId.equals(""))throw new RuntimeException("删除的ID为空");
			ModelStruct ms_=this.modelStructServiceImpl.getModelStructById(msId);
			if(ms_==null) throw new RuntimeException("删除的对象不存在,请刷新后再试");
			this.modelStructServiceImpl.deleteModelStruct(ms_);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	/**
	 * 根据ID获取对象
	 * @return
	 */
	public String getModelStructById(){
		try {
			if(msId==null||msId.equals(""))throw new RuntimeException("查询对象的ID为空");
			ModelStruct ms_=this.modelStructServiceImpl.getModelStructById(msId);
			if(ms_==null) throw new RuntimeException("查询的对象不存在,请刷新后再试");
			super.smsg=new SimpleMsg("获取对象成功",true,ms_);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	/**
	 * 获取集合
	 * @return
	 */
	public String getModelStructByList(){
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	/**
	 * 获取结构树
	 * @return
	 */
	public void getModelStructTree(){
		List lst=this.modelStructServiceImpl.getModelStructByList(null, -1, -1);
		Tree tree=new Tree();
		tree.setObjForm(lst, "msId", "msParentid");
		String str=this.modelStructServiceImpl.getInfo(tree, "-1");
		//super.strmsg=str;
		//log.debug(str);
		try {
			super.clientProxy.getResponse().setContentType("text/text;charset=utf-8");
			PrintWriter pw=super.clientProxy.getResponse().getWriter();
			pw.print(str);
			pw.flush();
			pw.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return super.STRING;
	}
	/**
	 * @param modelStructServiceImpl the modelStructServiceImpl to set
	 */
	public void setModelStructServiceImpl(ModelStructService modelStructServiceImpl) {
		this.modelStructServiceImpl = modelStructServiceImpl;
	}
	/**
	 * @return the ms
	 */
	public ModelStruct getMs() {
		return ms;
	}
	/**
	 * @param ms the ms to set
	 */
	public void setMs(ModelStruct ms) {
		this.ms = ms;
	}	

	/**
	 * @param msId the msId to set
	 */
	public void setMsId(String msId) {
		this.msId = msId;
	}	
}
