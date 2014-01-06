package com.sps.ps.commonbiz.pk.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.mapping.PrimaryKey;

import com.sps.ps.commonbiz.pk.entity.Primarykey;
import com.sps.ps.commonbiz.pk.service.Generate;
import com.sps.ps.commonbiz.pk.service.PrimarykeyService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.pk.service.impl.GenerateImpl;
import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;

public class PrimarykeyAction extends SysWebCtrlAction {
	private PrimarykeyService primarykeyServiceImpl;
	private static Logger log = Logger.getLogger(PrimarykeyAction.class);
    private Primarykey pk;
    private String pkId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Spring注入
	 * @param primarykeyServiceImpl the primarykeyServiceImpl to set
	 */
	public void setPrimarykeyServiceImpl(PrimarykeyService primarykeyServiceImpl) {
		this.primarykeyServiceImpl = primarykeyServiceImpl;
	}
	/**
	 * 保存Primarykey对象
	 * @return
	 */
	public String savePrimarykey(){
		try {
			if(pk==null){
				throw new RuntimeException("保存的[PK]对象不能为空");
			}
			if(pk.getPkId()==null||pk.getPkId().equals("")){
				int rows=this.primarykeyServiceImpl.countByCode(new String[]{pk.getPkCode()});
				if(rows>0){
					throw new RuntimeException("编码是唯一的,不能重复");
				}				
				pk.setPkId(GETKEY.getKey());
				this.primarykeyServiceImpl.addPrimarykey(pk);
				log.debug("新增成功----->");
			}else{
				this.primarykeyServiceImpl.updatePrimaryKey(pk);
				log.debug("更新成功----->");
			}
			super.smsg=new SimpleMsg("保存成功",true,pk);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg("保存失败",false,e.getMessage());
		}
		return super.AJAX_SUCCESS;
	}
	/**
	 * 获取数据列表
	 * @return
	 */
	public String getPrimarykeyByList(){
		try {
			int rowSize=this.primarykeyServiceImpl.countPrimaryKey(new String[]{});
			List lst=this.primarykeyServiceImpl.getPrimaryKeyByList(new String[]{}, start, limit);
			super.tmsg=new TableMsg(true,"",lst,rowSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	public String deletePrimarykey(){
		try {
			if(pkId==null||"".equals(pkId))throw new RuntimeException("实体ID不能为空");
			Primarykey pk_=this.primarykeyServiceImpl.getPrimaryKeyById(pkId);
			if(pk_==null){
				throw new RuntimeException("删除失败,可能已经被删除,请刷新后再试");
			}
			this.primarykeyServiceImpl.deletePrimaryKey(pk_);
			super.smsg=new SimpleMsg("删除成功",true,null);
		} catch (Exception e) {
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),false,null);
		}
		return super.DELETE_SUCCESS;
	}
	/**
	 * @param pk the pk to set
	 */
	public void setPk(Primarykey pk) {
		this.pk = pk;
	}
	/**
	 * @return the pk
	 */
	public Primarykey getPk() {
		return pk;
	}
	/**
	 * @param pkId the pkId to set
	 */
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

}
