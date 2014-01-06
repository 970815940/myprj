package com.sysps.ps.workflow.entity;

import java.util.Set;

/**
 * SspWfCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SspWfCategory implements java.io.Serializable {

	// Fields
	/**
	 * 流程分类
	 */
	public static final String CATEGORY_TYPE_C="c";
	/**
	 * 流程定义
	 */
	public static final String CATEGORY_TYPE_p="p";
	private Long id;
	private String code;
	private String name;
	private String type;
	private Long parent;
	private Set<SspWfCategory> set;

	// Constructors

	/** default constructor */
	public SspWfCategory() {
	}

	/**
	 * @return the set
	 */
	public Set<SspWfCategory> getSet() {
		return set;
	}

	/**
	 * @param set the set to set
	 */
	public void setSet(Set<SspWfCategory> set) {
		this.set = set;
	}

	/** minimal constructor */
	public SspWfCategory(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SspWfCategory(Long id, String code, String name, String type,
			Long parent) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.parent = parent;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParent() {
		return this.parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

}