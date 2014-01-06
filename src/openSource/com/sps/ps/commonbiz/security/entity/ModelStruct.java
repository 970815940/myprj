package com.sps.ps.commonbiz.security.entity;

/**
 * ModelStruct entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ModelStruct implements java.io.Serializable {

	// Fields

	private String msId;
	private String msParentid;
	private String msDisplayname;
	private String msState;
	private String msDemo;
	private String msType;
	private String msIconurl;//MS_ICONURL

	// Constructors

	/** default constructor */
	public ModelStruct() {
	}

	/** minimal constructor */
	public ModelStruct(String msId) {
		this.msId = msId;
	}

	/** full constructor */
	public ModelStruct(String msId, String msParentid, String msDisplayname,
			String msState, String msDemo, String msType) {
		this.msId = msId;
		this.msParentid = msParentid;
		this.msDisplayname = msDisplayname;
		this.msState = msState;
		this.msDemo = msDemo;
		this.msType = msType;
	}

	// Property accessors

	public String getMsId() {
		return this.msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}

	public String getMsParentid() {
		return this.msParentid;
	}

	public void setMsParentid(String msParentid) {
		this.msParentid = msParentid;
	}

	public String getMsDisplayname() {
		return this.msDisplayname;
	}

	public void setMsDisplayname(String msDisplayname) {
		this.msDisplayname = msDisplayname;
	}

	public String getMsState() {
		return this.msState;
	}

	public void setMsState(String msState) {
		this.msState = msState;
	}

	public String getMsDemo() {
		return this.msDemo;
	}

	public void setMsDemo(String msDemo) {
		this.msDemo = msDemo;
	}

	public String getMsType() {
		return this.msType;
	}

	public void setMsType(String msType) {
		this.msType = msType;
	}

	public String getMsIconurl() {
		return msIconurl;
	}

	public void setMsIconurl(String msIconurl) {
		this.msIconurl = msIconurl;
	}

}