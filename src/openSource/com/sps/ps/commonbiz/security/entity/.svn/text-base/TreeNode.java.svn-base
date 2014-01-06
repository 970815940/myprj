package com.sps.ps.commonbiz.security.entity;

import java.util.ArrayList;
import java.util.List;

import com.ibm.db2.jcc.t4.ob;

public class TreeNode {
	private String id;
	private String name;
	private String pid;
	private Object attribute;
	private boolean leaf;
	private List children=new ArrayList();
	public void addChild(TreeNode tn){
		this.children.add(tn);
	}
	public void setLeaf(boolean leaf){
		this.leaf=leaf;
	}
	
	public boolean getLeaf() {
		return leaf;
	}
	public Object getAttribute() {
		return attribute;
	}
	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
}
