package com.sps.ps.commonbiz.security.entity;

import java.util.ArrayList;
import java.util.List;

import com.ibm.db2.jcc.t4.ob;

/***
 * 此类专门用于构建一颗资源树
 * @author taoxs
 *
 */
public class ModelTree {
	private List data;
	public String getId(Object obj){
		String id="";
		try {
			if(obj instanceof ModelStruct){
				ModelStruct ms_=(ModelStruct) obj;
				id=ms_.getMsId();
			}else if(obj instanceof ModelEntity){
				ModelEntity me_=(ModelEntity)obj;
				id=me_.getMeId();
			}else{
				throw new RuntimeException("资源树ID获取失败");
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	public String getParentId(Object obj){
		String id="";
		try {
			if(obj instanceof ModelStruct){
				ModelStruct ms_=(ModelStruct) obj;
				id=ms_.getMsParentid();
			}else if(obj instanceof ModelEntity){
				ModelEntity me_=(ModelEntity)obj;
				id=me_.getMeMsid();
			}else{
				throw new RuntimeException("资源树ID获取失败");
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;		
	}
	/**
	 * 获取顶级节点
	 * @param obj
	 * @return
	 */
	public boolean getTopNode(Object obj){
		String pid="";
		try {
			if(obj instanceof ModelStruct){
				ModelStruct ms_=(ModelStruct) obj;
				pid=ms_.getMsParentid();
			}else if(obj instanceof ModelEntity){
				ModelEntity me_=(ModelEntity)obj;
				pid=me_.getMeMsid();
			}else{
				throw new RuntimeException("资源树构建失败");
			}
			return pid.equals("-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/***
	 * 获取第一级根节点
	 */
	public void getRootNode(){
		List list=new ArrayList();
		if(data==null)throw new RuntimeException("树构建失败");
		for (int i = 0; i <data.size(); i++) {
			boolean flay=getTopNode(data.get(i));
			if(flay){
				list.add(data.get(i));
			}
		}
		//
		for (int i = 0; i <list.size(); i++) {
			Object obj=list.get(i);
			TreeNode tree=new TreeNode();
			tree.setId(getId(obj));
			tree.setPid(getParentId(obj));
			tree.setAttribute(obj);
			LoadChildForRoot(obj);
		}
	}
	/***
	 * 根据根节点加载子节点
	 */
	public void LoadChildForRoot(Object obj){
		
	}
}
