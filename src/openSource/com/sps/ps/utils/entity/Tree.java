package com.sps.ps.utils.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建树的辅助实体类[2]
 * @author taoxs
 *
 */
public class Tree {
	private String id;
	private String pid;
	private List<entity> tmpList=new ArrayList<entity>();
	/**
	 * 根据PID获取所有的子节点
	 * @param pid
	 * @return
	 */
	public List getChildrenById(String pid){
		List list=new ArrayList();
		if(pid==null){
			return null;
		}
		if(tmpList==null){
			throw new  RuntimeException("tmpList is not null");
		}
		for (int i = 0; i <tmpList.size(); i++) {
			entity entity=tmpList.get(i);
			if(entity.getPid().equals(pid)){
				String curId=tmpList.get(i).getId();
				list.add(curId);
				if(hasChildren(curId)){
					list.addAll(getChildrenById(curId));
				}
			}
		}
		return list;
	}
	/**
	 * 根据节点ID 获取子项
	 * @param pid
	 * @return
	 */
	public List getChildrens(String pid){
		List list=new ArrayList();
		if(pid==null){
			return null;
		}
		for (int i = 0; i <tmpList.size(); i++) {
			if(tmpList.get(i).getPid().equals(pid)){
				list.add(tmpList.get(i).getObj());
			}
		}
		return list;
	}
	/**
	 * 设置实体  单个对象
	 * @param obj
	 * @param id
	 * @param pid
	 */
	public void setObj(Object obj,String id,String pid){
		if(obj==null){
			throw new RuntimeException("obj is not null!");
		}
		entity e=new entity();
		e.setId(id);
		e.setPid(pid);
		e.setObj(obj);
		tmpList.add(e);
	}
	/**
	 * 设置实体  多个对象
	 * @param obj
	 * @param id
	 * @param pid
	 */
	public void setObj(List lst,String id,String pid){
		if(lst==null){
			throw new RuntimeException("list is not null!");
		}
		for (int i = 0; i <lst.size(); i++) {
			setObj(lst.get(i),id,pid);
		}
	}	
	/**
	 * 设置实体值  单个对象
	 * @param obj
	 * @param id
	 * @param pid
	 */
	public void setOjbForm(Object obj,String id,String pid){
		if(obj==null){
			throw new RuntimeException("object  is not null!");
		}
		entity e=new entity();
		e.setObj(obj);
		try {
			Field idf=obj.getClass().getDeclaredField(id);
			idf.setAccessible(true);
			String idV=idf==null?null:idf.get(obj).toString();
			Field pidf=obj.getClass().getDeclaredField(pid);
			pidf.setAccessible(true);
			String pidV=pidf==null?null:pidf.get(obj).toString();
			e.setId(idV);
			e.setPid(pidV);
			System.out.println(idV+"==="+pidV);
			tmpList.add(e);
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e223) {
			// TODO Auto-generated catch block
			e223.printStackTrace();
		} catch (IllegalAccessException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
	}
	/**
	 * 设置实体值  多个对象
	 * @param list
	 * @param id
	 * @param pid
	 */
	public void setObjForm(List list,String id,String pid){
		if(list==null){
			throw new RuntimeException("list is not null!");
		}
		for (int i = 0; i < list.size(); i++) {
			setOjbForm(list.get(i), id, pid);
		}
	}
	
	/**
	 * 判断是否有子节点
	 * @param pid
	 * @return
	 */
	public boolean hasChildren(String pid){
		if(pid==null){
			return false;
		}
		boolean ishas=false;
		for (int i = 0; i <tmpList.size(); i++) {
			if(tmpList.get(i).getPid().equals(pid)){
				ishas=true;
				break;
			}
		}
		return ishas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List getTmpList() {
		return tmpList;
	}
	public void setTmpList(List tmpList) {
		this.tmpList = tmpList;
	}
}
