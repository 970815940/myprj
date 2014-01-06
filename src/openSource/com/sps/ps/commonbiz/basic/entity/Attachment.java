package com.sps.ps.commonbiz.basic.entity;

/**
 * Attachment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Attachment implements java.io.Serializable {

	// Fields

	private String id;
	private String model;
	private String businessid;
	private String upload;
	private String name;
	private Double size;

	// Constructors

	/** default constructor */
	public Attachment() {
	}

	/** minimal constructor */
	public Attachment(String id) {
		this.id = id;
	}

	/** full constructor */
	public Attachment(String id, String model, String businessid,
			String upload, String name, Double size) {
		this.id = id;
		this.model = model;
		this.businessid = businessid;
		this.upload = upload;
		this.name = name;
		this.size = size;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getUpload() {
		return this.upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSize() {
		return this.size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

}