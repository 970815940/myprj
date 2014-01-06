package com.sps.ps.commonbiz.security.entity;
/***
 * 用户登录对象
 * @author Administrator
 *
 */
public class LoginUser implements java.io.Serializable{
	private String guid;//sysorg 唯一
	private String userId;//用户表ID
	private String loginUserId;//用户登录ID
	private String userName;//用户姓名
	private String dptId;//部门ID
	private String dptName;//部门名称
	private String stationId;//岗位ID
	private String stationName;//岗位名称
	private String orgId;//机构ID
	private String orgName;//机构名称
	public LoginUser (){}
	public LoginUser(String guid, String userId, String loginUserId,
			String userName, String dptId, String dptName, String stationId,
			String stationName, String orgId, String orgName) {
		this.guid = guid;
		this.userId = userId;
		this.loginUserId = loginUserId;
		this.userName = userName;
		this.dptId = dptId;
		this.dptName = dptName;
		this.stationId = stationId;
		this.stationName = stationName;
		this.orgId = orgId;
		this.orgName = orgName;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDptId() {
		return dptId;
	}
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	public String getDptName() {
		return dptName;
	}
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
