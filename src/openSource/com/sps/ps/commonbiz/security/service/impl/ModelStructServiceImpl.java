package com.sps.ps.commonbiz.security.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.security.dao.ModelEntityIDAO;
import com.sps.ps.commonbiz.security.dao.ModelStructIDAO;
import com.sps.ps.commonbiz.security.entity.ModelEntity;
import com.sps.ps.commonbiz.security.entity.ModelStruct;
import com.sps.ps.commonbiz.security.entity.TreeNode;
import com.sps.ps.commonbiz.security.service.ModelEntityService;
import com.sps.ps.commonbiz.security.service.ModelStructService;
import com.sps.ps.utils.entity.Tree;


public class ModelStructServiceImpl implements ModelStructService {
	private ModelStructIDAO modelStructDAOImpl;
	private ModelEntityService modelEntityServiceImpl;
	/**
	 * SPRING注入dao IMpl
	 * @param modelStructDAOImpl the modelStructDAOImpl to set
	 */
	public void setModelStructDAOImpl(ModelStructIDAO modelStructDAOImpl) {
		this.modelStructDAOImpl = modelStructDAOImpl;
	}

	public void addModelStruct(ModelStruct ms) {
		this.modelStructDAOImpl.addModelStruct(ms);
		
	}

	public void deleteModelStruct(ModelStruct ms) {
		this.modelStructDAOImpl.deleteModelStruct(ms);
		String msId=ms.getMsId();
		//删除结构的同时，要把相关联的功能数据全部删除
		this.modelEntityServiceImpl.deleteModelEntitys(new String[]{"meMsid"}, new String[]{"="}, new String[]{msId});

	}

	public ModelStruct getModelStructById(String id) {
		
		return this.modelStructDAOImpl.getModelStructById(id);
	}

	public List getModelStructByList(String[] values, int start, int size) {
		String hql="from ModelStruct";
		return this.modelStructDAOImpl.getModelStructByList(hql, values, start, size);
	}

	public void updateModelStruct(ModelStruct ms) {
		this.modelStructDAOImpl.updateModelStruct(ms);
		
	}
	public  String getInfo(Tree tree,String id){
		String str="";
		try {
			
			List tmpList=tree.getChildrens(id);
			if(tmpList.size()>0){
				str+="[";
				for (int i = 0; i < tmpList.size(); i++) {
					ModelStruct node=(ModelStruct) tmpList.get(i);
					boolean leaf=tree.hasChildren(node.getMsId());
					str+="{";
					str+="id:\""+node.getMsId()+"\",";
					str+="text:\""+node.getMsDisplayname()+"\",";
					str+="leaf:"+!leaf+",";
					str+="msType:"+node.getMsType();
					str+="";
					if(leaf){
						str+=",children:"+getInfo(tree,node.getMsId());
					}
					str+="},";
					if(tmpList.size()-1==i){
						str = str.replaceAll(",$", "");
					}
					
				}
				if(!str.equals("")){
					str+="]";
				}
				
			}
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @param modelEntityServiceImpl the modelEntityServiceImpl to set
	 */
	public void setModelEntityServiceImpl(ModelEntityService modelEntityServiceImpl) {
		this.modelEntityServiceImpl = modelEntityServiceImpl;
	}	

}
