package com.sps.ps.commonbiz.basic.entity;

/**
 * Workspanceporlet entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Workspanceporlet implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Portlet portletid;
	private String userid;
	private String position;
	private Integer index;

	// Constructors

	/** default constructor */
	public Workspanceporlet() {
	}

	/** minimal constructor */
	public Workspanceporlet(String id) {
		this.id = id;
	}

	/** full constructor */
	public Workspanceporlet(String id,  String userid,
			String position, Integer index) {
		this.id = id;
		this.userid = userid;
		this.position = position;
		this.index = index;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Portlet getPortletid() {
		return portletid;
	}

	public void setPortletid(Portlet portletid) {
		this.portletid = portletid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}