package com.sps.ps.commonbiz.security.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.commonbiz.security.entity.ModelEntity;
import com.sps.ps.commonbiz.security.entity.ModelRight;
import com.sps.ps.commonbiz.security.entity.ModelStruct;
import com.sps.ps.commonbiz.security.entity.Tree;
import com.sps.ps.commonbiz.security.service.AuthorizeService;
import com.sps.ps.commonbiz.security.service.ModelEntityService;
import com.sps.ps.commonbiz.security.service.ModelRightService;
import com.sps.ps.commonbiz.security.service.ModelStructService;


public class AuthorizeServiceImpl implements AuthorizeService {
	/**
	 * 功能实体 CUID服务
	 */
	private ModelEntityService modelEntityServiceImpl;
	/**
	 * 功能结构 CUID服务
	 */
	private ModelStructService modelStructServiceImpl;
	/**
	 * 系统组织机构  CUID服务
	 */
	private SysOrgService sysOrgServiceImpl;
	/**
	 * 授权管理 GUID 操作
	 */
	private ModelRightService modelRightServiceImpl;
	
	
	
	
	
	private List StructList;//装载实体功能与结构相关联的容器
	
	public void setModelRightServiceImpl(ModelRightService modelRightServiceImpl) {
		this.modelRightServiceImpl = modelRightServiceImpl;
	}
	public void setModelEntityServiceImpl(ModelEntityService modelEntityServiceImpl) {
		this.modelEntityServiceImpl = modelEntityServiceImpl;
	}
	public void setModelStructServiceImpl(ModelStructService modelStructServiceImpl) {
		this.modelStructServiceImpl = modelStructServiceImpl;
	}
	public void setSysOrgServiceImpl(SysOrgService sysOrgServiceImpl) {
		this.sysOrgServiceImpl = sysOrgServiceImpl;
	}
	public String getAutorization(String orgGuid) {
		SysOrg so_=this.sysOrgServiceImpl.getSysOrgById(orgGuid);
		if(so_==null)throw new RuntimeException("组织机构不存在,获取未权限失败");
		//得到已授权的所有叶子功能
		List lst_Unmr=this.modelRightServiceImpl.getModelRightByList(new String[]{"mrOrgguid"}, new String[]{"="}, new String[]{so_.getSoGuid()}, -1,-1);
		//下面对叶子节点进行循环向上找父节点。
		List lst_ms=this.modelStructServiceImpl.getModelStructByList(null, -1,-1);
		List lst_me=new ArrayList();//根据授权表的功能ID 得到功能对象
		List list_relation_mix=new ArrayList();//保留功能和结构有关联的对象集合
		Map me_map=new HashMap();
		for (int i = 0; i <lst_ms.size(); i++) {
			ModelStruct ms__=(ModelStruct) lst_ms.get(i);
			me_map.put(ms__.getMsId(), ms__);
		}
		//获取相关联的功能，和结构
		for (int i = 0; i <lst_Unmr.size(); i++) {
			ModelRight mr_=(ModelRight) lst_Unmr.get(i);
			ModelEntity me__=this.modelEntityServiceImpl.getModelEntityById(mr_.getMrMeid());
			lst_me.add(me__);
			getRelation_mixList(list_relation_mix,me_map,me__.getMeMsid());
		}
		list_relation_mix.addAll(lst_me);
		Tree tree=new Tree();
		tree.setObjForm(list_relation_mix);
		String treeStr=getInfo(tree, "-1");
		return treeStr;		
	}
	public String getUnAutorization(String orgGuid) {
		try {
			SysOrg so_=this.sysOrgServiceImpl.getSysOrgById(orgGuid);
			if(so_==null)throw new RuntimeException("组织机构不存在,获取未权限失败");
			//得到已授权的所有叶子功能
			List lst_Unmr=this.modelRightServiceImpl.getModelRightByList(new String[]{"mrOrgguid"}, new String[]{"="}, new String[]{so_.getSoGuid()}, -1,-1);
			String hql=null;
			if(lst_Unmr==null||lst_Unmr.size()==0){
				hql="from ModelEntity";
			}else{
				hql="from ModelEntity where meId not in (:meIds)";
			}
			List cc=new ArrayList();
			for (int i = 0; i <lst_Unmr.size(); i++) {
				ModelRight mr_=(ModelRight) lst_Unmr.get(i);
				cc.add(mr_.getMrMeid());
			}
			//下面对叶子节点进行循环向上找父节点。
			List lst_ms=this.modelStructServiceImpl.getModelStructByList(null, -1,-1);//获取所有的结构
			List lst_me=this.modelEntityServiceImpl.getUnModelRight(hql, cc);//得到未授权的所有功能
			List list_relation_mix=new ArrayList();//保留功能和结构有关联的对象集合
			Map me_map=new HashMap();
			for (int i = 0; i <lst_ms.size(); i++) {//把全部结构 组装为MAP结构
				ModelStruct ms__=(ModelStruct) lst_ms.get(i);
				me_map.put(ms__.getMsId(), ms__);
			}
			//获取相关联的功能，和结构
			for (int i = 0; i <lst_me.size(); i++) {
				ModelEntity me__=(ModelEntity) lst_me.get(i);
				getRelation_mixList(list_relation_mix,me_map,me__.getMeMsid());
			}
			list_relation_mix.addAll(lst_me);	
			Tree tree=new Tree();
			tree.setObjForm(list_relation_mix);
			String treeStr=getInfo(tree, "-1");
			return treeStr;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	public String getInfo(Tree tree,String id){
		StringBuffer sb=new StringBuffer();
		List lst=tree.getChildrens(id);
		if(lst!=null&&lst.size()>0){
			sb.append("[");
			for (int i = 0; i < lst.size(); i++) {
				Object obj=lst.get(i);
				boolean leaf=false;
				sb.append("{");
				if(obj instanceof ModelEntity){
					ModelEntity me_=(ModelEntity)obj;
					leaf=tree.hasChildren(me_.getMeId());
					sb.append("id:\""+me_.getMeId()+"\",");
					sb.append("text:\""+me_.getMeDispalyname()+"\",");
					sb.append("leaf:"+!leaf+",");
					sb.append("icon_url:\""+me_.getMeIconurl()+"\",");
					sb.append("href_url:\""+me_.getMeUrl()+"\"");
					if(leaf){
						sb.append(",children:"+getInfo(tree, me_.getMeId()));
					}
				}else if(obj instanceof ModelStruct){
					ModelStruct ms_=(ModelStruct)obj;
					leaf=tree.hasChildren(ms_.getMsId());	
					sb.append("id:\""+ms_.getMsId()+"\",");
					sb.append("text:\""+ms_.getMsDisplayname()+"\",");
					sb.append("icon_url:\""+ms_.getMsIconurl()+"\",");
					sb.append("leaf:"+!leaf);
					if(leaf){
						sb.append(",children:"+getInfo(tree, ms_.getMsId()));
					}					
				}
				sb.append("}");
				if(lst.size()-1!=i){
					sb.append(",");
				}
				
				
			}
			sb.append("]");
		}
		return sb.toString();
	}
	/***
	 *  获取有关联的集合 <br/>
	 * <b>该集合包含了功能实体,功能结构</b>
	 * @param list_relation_mix
	 * @param me_map
	 * @param key
	 */
	public void getRelation_mixList(List list_relation_mix,Map me_map,String key){
			try {
				ModelStruct ms__=(ModelStruct) me_map.get(key);
				if(!list_relation_mix.contains(ms__)){
					list_relation_mix.add(ms__);
				}
				if(!ms__.getMsParentid().equals("-1"))getRelation_mixList(list_relation_mix,me_map,ms__.getMsParentid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	/**
	 * 根据模块功能实例集合 获取模块结构
	 * @param me_lst
	 * @return
	 */
	public void getModelStructByModelEntitys(List me_lst){
		if(me_lst==null||me_lst.size()==0)return;
		//获取所有的结构
		List modelStruct_lst=this.modelStructServiceImpl.getModelStructByList(null, -1, -1);
		//保留相关联的 模块实体功能的可视结构
		//把结构组装为key value的形式
		Map<String,ModelStruct> StructAllmap=new HashMap<String,ModelStruct>();
		for (int i = 0; i < modelStruct_lst.size(); i++) {
			ModelStruct ms_=(ModelStruct) modelStruct_lst.get(i);
			StructAllmap.put(ms_.getMsId(), ms_);
		}
		//
		this.StructList=new ArrayList();//清空容器 重新装载
		for (int i = 0; i < me_lst.size(); i++) {
			ModelEntity mes_=(ModelEntity) me_lst.get(i);
			getMe_Ms_(mes_.getMeMsid(),StructAllmap);
		}
	}
	/***
	 * 获取功能实体与结构相关联的结构集合
	 * @param pid
	 * @param StructAllmap
	 * @return
	 */
	public void getMe_Ms_(String pid,Map<String,ModelStruct> StructAllmap){
		ModelStruct ms_=StructAllmap.get(pid);
		StructList.add(ms_);
		if(!ms_.getMsParentid().equals("-1")){
			getMe_Ms_(ms_.getMsParentid(), StructAllmap);
		}
	}
	/**
	 * 根据权限数据集合，获取模块实例集合
	 * @param mr_lst 模块实体功能对象
	 * @return
	 */
	public List<ModelEntity> getModelEntityByModelRights(List mr_lst){
		if(mr_lst==null||mr_lst.size()==0)return new ArrayList();
		List<ModelEntity> lst=new ArrayList<ModelEntity>();
		for (int i = 0; i < mr_lst.size(); i++) {
			ModelRight mr__=(ModelRight) mr_lst.get(i);
			//获取模块实体信息
			ModelEntity me_=this.modelEntityServiceImpl.getModelEntityById(mr__.getMrOrgguid());
			lst.add(me_);
		}
		return lst;
	}
	/**
	 * 根据GUID数组 获取全部权限
	 * @return
	 */
	public List getAutorizationOrgGruids(String[] gruids){
		if(gruids==null||gruids.length==0)return new ArrayList();
		String hql="from SysOrg where soGuid in (:gruids) order by soIndex";
		List lst=this.sysOrgServiceImpl.getSysForHql_In(hql, "gruids", gruids);
		
		//获取机构上的权限模块                             
		String hql_org_power="from ModelRight where mrOrgguid in (:mrOrgguid) and mrType = :mrType";
		List power=this.modelRightServiceImpl.getModelRightByGuidAndType(hql, new String[]{"mrOrgguid","mrType"}, new Object[]{Arrays.asList(gruids),ModelRight.MR_ORG});
		if(power!=null){
			
			
		}
		
		
		return power;
	}
	public void saveAutorization(String orgGUID, String ModelEntityId) {
		try {
			SysOrg so_=this.sysOrgServiceImpl.getSysOrgById(orgGUID);
			if(so_==null)return;
			String[] meIds=ModelEntityId.split(",");
			for (int i = 0; i < meIds.length; i++) {
				ModelRight mr_=new ModelRight();
				mr_.setMrId(GETKEY.getKey("model-right"));
				mr_.setMrMeid(meIds[i]);
				mr_.setMrOrgguid(orgGUID);
				//if(so_.getSoOrgkind().equals("user")){
					//授权类型 待定
				//}
				this.modelRightServiceImpl.addModelRight(mr_);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("授权失败,"+e.getMessage());
		}
	}
	public void saveUnAutorization(String orgGUID, String modelEntityId) {
		try {
			SysOrg so_=this.sysOrgServiceImpl.getSysOrgById(orgGUID);
			if(so_==null)return;
			String[] meIds=modelEntityId.split(",");
			String hql="delete ModelRight where mrOrgguid='"+orgGUID+"' and mrMeid in (:meIds)";
			this.modelRightServiceImpl.deleteModelRIghtByGuidAndModelEntityId(hql, meIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("取消权限失败,"+e.getMessage());
		}
		
	}
}
