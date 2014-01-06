package com.sps.ps.commonbiz.security.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.security.entity.ModelEntity;
import com.sps.ps.commonbiz.security.service.ModelEntityService;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class ModelEntityAction extends SysWebCtrlAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ModelEntityAction.class);
	private ModelEntityService modelEntityServiceImpl;
	
	
	private String meMsid;//资源结构ID
	private ModelEntity me;
	private String meId;//主键

	/**
	 * 保存对象
	 * @return
	 */
	public String saveModelEntity(){
		try {
			if(me==null)throw new RuntimeException("保存的对象不能为空");
			if(me.getMeId()!=null&&!"".equals(me.getMeId())){//执行UPDATE
				ModelEntity me__=this.modelEntityServiceImpl.getModelEntityById(me.getMeId());
				me.setMeCreatetime(me__.getMeCreatetime());
				this.modelEntityServiceImpl.updateModelEntity(me);
				log.debug("业务实体[ID="+me.getMeId()+"]更新成功");
			}else{//执行INSERT
				me.setMeId(GETKEY.getKey("model-entity"));
				me.setMeCreatetime(new Date());
				this.modelEntityServiceImpl.addModelEntity(me);
				log.debug("新增业务实体成功[ID="+me.getMeId()+"]");
			}
			super.smsg=new SimpleMsg("保存成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.ADD_SUCCESS;
	}
	/**
	 * 删除对象
	 * @return
	 */
	public String deleteModelEntity(){
		try {
			if(meId==null||meId.equals("")){
				throw new RuntimeException("ID不能为空");
			}
			ModelEntity me_=this.modelEntityServiceImpl.getModelEntityById(meId);
			this.modelEntityServiceImpl.deleteModelEntity(me_);
			super.smsg=new SimpleMsg("删除成功",true,null);
			log.debug("删除成功");
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
	public String getModelEntityById(){
		try {
			if(meId==null||meId.equals("")){
				throw new RuntimeException("ID不能为空");
			}
			ModelEntity me_=this.modelEntityServiceImpl.getModelEntityById(meId);
			super.smsg=new SimpleMsg("根据ID获取对象成功",true,me_);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.FIND_BY_OBJID_SUCCESS;
	}
	/**
	 * 获取集合列表
	 * @return
	 */
	public String getModelEntityByList(){
		try {
			int rowcount=this.modelEntityServiceImpl.countModelEntityByList(new String[]{},new  String[]{},new String[]{});
			List list=this.modelEntityServiceImpl.getModelEntityByList(new String[]{}, new String[]{},new String[]{}, start, limit);
			super.tmsg=new TableMsg(true,"",list,rowcount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	/**
	 * 根据资源结构查询功能列表·
	 * @return
	 */
	public String getModelEntityForMSByList(){
		try {
			int rowcount=this.modelEntityServiceImpl.countModelEntityByList(new String[]{"meMsid"}, new String[]{"="},new String[]{meMsid});
			List list=this.modelEntityServiceImpl.getModelEntityByList(new String[]{"meMsid"}, new String[]{"="},new String[]{meMsid}, start, limit);
			super.tmsg=new TableMsg(true,"",list,rowcount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.tmsg=new TableMsg(true,e.getMessage(),null,0);
		}		
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	
	
	
	/**
	 * @param modelEntityServiceImpl the modelEntityServiceImpl to set
	 */
	public void setModelEntityServiceImpl(ModelEntityService modelEntityServiceImpl) {
		this.modelEntityServiceImpl = modelEntityServiceImpl;
	}
	/**
	 * @param meMsid the meMsid to set
	 */
	public void setMeMsid(String meMsid) {
		this.meMsid = meMsid;
	}
	/**
	 * @return the me
	 */
	public ModelEntity getMe() {
		return me;
	}
	/**
	 * @param me the me to set
	 */
	public void setMe(ModelEntity me) {
		this.me = me;
	}

	/**
	 * @param meId the meId to set
	 */
	public void setMeId(String meId) {
		this.meId = meId;
	}
}
