package com.sps.ps.utils.entity;

import java.util.List;

/**
 * 正对Extjs grid 表格设计的消息返回实体
 * @author taoxs
 * @since 1.0
 */
public class TableMsg {
	private boolean success;
	private String msg;
	private List root;
	private int allRowCount;
	public TableMsg(List root, int allRowCount) {
		super();
		this.root = root;
		this.allRowCount = allRowCount;
	}	
	public TableMsg(String msg,boolean success){
		this.msg=msg;
		this.success=success;
	}
	public TableMsg(boolean success, String msg, List root, int allRowCount) {
		super();
		this.success = success;
		this.msg = msg;
		this.root = root;
		this.allRowCount = allRowCount;
	}
	/**
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the root
	 */
	public List getRoot() {
		return root;
	}
	/**
	 * @param root the root to set
	 */
	public void setRoot(List root) {
		this.root = root;
	}
	/**
	 * @return the rowCount
	 */
	/**
	 * @return the allRowCount
	 */
	public int getAllRowCount() {
		return allRowCount;
	}
	/**
	 * @param allRowCount the allRowCount to set
	 */
	public void setAllRowCount(int allRowCount) {
		this.allRowCount = allRowCount;
	}

}
