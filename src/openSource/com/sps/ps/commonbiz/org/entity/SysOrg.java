package com.sps.ps.commonbiz.org.entity;

/**
 * SysOrg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysOrg implements java.io.Serializable {

	// Fields

	private String soGuid;
	private String soParentid;
	private Integer soIndex;
	private String soDptid;
	private String soOrgkind;
	private String soOrgpath;
	private String soOrgdispalyname;
	private String soStation;
	private String soUser;
	private String soOrg;

	// Constructors

	/** default constructor */
	public SysOrg() {
	}

	/** minimal constructor */
	public SysOrg(String soGuid) {
		this.soGuid = soGuid;
	}

	/** full constructor */
	public SysOrg(String soGuid, String soParentid, Integer soIndex,
			String soDptid, String soOrgkind, String soOrgpath,
			String soOrgdispalyname, String soStation, String soUser,
			String soOrg) {
		this.soGuid = soGuid;
		this.soParentid = soParentid;
		this.soIndex = soIndex;
		this.soDptid = soDptid;
		this.soOrgkind = soOrgkind;
		this.soOrgpath = soOrgpath;
		this.soOrgdispalyname = soOrgdispalyname;
		this.soStation = soStation;
		this.soUser = soUser;
		this.soOrg = soOrg;
	}

	// Property accessors

	public String getSoGuid() {
		return this.soGuid;
	}

	public void setSoGuid(String soGuid) {
		this.soGuid = soGuid;
	}

	public String getSoParentid() {
		return this.soParentid;
	}

	public void setSoParentid(String soParentid) {
		this.soParentid = soParentid;
	}

	public Integer getSoIndex() {
		return this.soIndex;
	}

	public void setSoIndex(Integer soIndex) {
		this.soIndex = soIndex;
	}

	public String getSoDptid() {
		return this.soDptid;
	}

	public void setSoDptid(String soDptid) {
		this.soDptid = soDptid;
	}

	public String getSoOrgkind() {
		return this.soOrgkind;
	}

	public void setSoOrgkind(String soOrgkind) {
		this.soOrgkind = soOrgkind;
	}

	public String getSoOrgpath() {
		return this.soOrgpath;
	}

	public void setSoOrgpath(String soOrgpath) {
		this.soOrgpath = soOrgpath;
	}

	public String getSoOrgdispalyname() {
		return this.soOrgdispalyname;
	}

	public void setSoOrgdispalyname(String soOrgdispalyname) {
		this.soOrgdispalyname = soOrgdispalyname;
	}

	public String getSoStation() {
		return this.soStation;
	}

	public void setSoStation(String soStation) {
		this.soStation = soStation;
	}

	public String getSoUser() {
		return this.soUser;
	}

	public void setSoUser(String soUser) {
		this.soUser = soUser;
	}

	public String getSoOrg() {
		return this.soOrg;
	}

	public void setSoOrg(String soOrg) {
		this.soOrg = soOrg;
	}

}