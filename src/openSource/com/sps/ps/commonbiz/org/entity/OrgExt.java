package com.sps.ps.commonbiz.org.entity;
/**
 * 组织机构  -->扩展实体表 用于TREEnode节点
 * @author taoxs
 *
 */
public class OrgExt {
	private String id;
	private String text;
	private String icon;
	private boolean leaf=false;
	
	//对象属性
	private SysOrg so;
	/**
	 * @param id
	 * @param text
	 * @param so
	 */
	public OrgExt(String id, String text, SysOrg so,String icon) {
		this.id = id;
		this.text = text;
		this.so = so;
		this.icon=icon;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the so
	 */
	public SysOrg getSo() {
		return so;
	}
	/**
	 * @param so the so to set
	 */
	public void setSo(SysOrg so) {
		this.so = so;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}
	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
}
