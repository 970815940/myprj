package com.sps.ps.commonbiz.org.entity;

/**
 * SysStation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysStation implements java.io.Serializable {

	// Fields

	private String guid;
	private String ssId;
	private String ssDispalyname;
	private String ssDemo;
	private String ssState;
	private Integer ssIndex;

	// Constructors

	/** default constructor */
	public SysStation() {
	}

	/** minimal constructor */
	public SysStation(String guid) {
		this.guid = guid;
	}

	/** full constructor */
	public SysStation(String guid, String ssId, String ssDispalyname,
			String ssDemo, String ssState, Integer ssIndex) {
		this.guid = guid;
		this.ssId = ssId;
		this.ssDispalyname = ssDispalyname;
		this.ssDemo = ssDemo;
		this.ssState = ssState;
		this.ssIndex = ssIndex;
	}

	// Property accessors

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSsId() {
		return this.ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public String getSsDispalyname() {
		return this.ssDispalyname;
	}

	public void setSsDispalyname(String ssDispalyname) {
		this.ssDispalyname = ssDispalyname;
	}

	public String getSsDemo() {
		return this.ssDemo;
	}

	public void setSsDemo(String ssDemo) {
		this.ssDemo = ssDemo;
	}

	public String getSsState() {
		return this.ssState;
	}

	public void setSsState(String ssState) {
		this.ssState = ssState;
	}

	public Integer getSsIndex() {
		return this.ssIndex;
	}

	public void setSsIndex(Integer ssIndex) {
		this.ssIndex = ssIndex;
	}

}