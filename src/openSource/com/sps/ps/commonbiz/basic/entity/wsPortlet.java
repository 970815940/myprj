package com.sps.ps.commonbiz.basic.entity;

public class wsPortlet {
	private Portlet portlet;
	private String istrue;
	public Portlet getPortlet() {
		return portlet;
	}
	public void setPortlet(Portlet portlet) {
		this.portlet = portlet;
	}
	public String getIstrue() {
		return istrue;
	}
	public void setIstrue(String istrue) {
		this.istrue = istrue;
	}
	public wsPortlet(Portlet portlet, String istrue) {
		this.portlet = portlet;
		this.istrue = istrue;
	}
	public wsPortlet(){}
}
