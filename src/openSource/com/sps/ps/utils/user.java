package com.sps.ps.utils;

public class user {
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param name
	 * @param age 
	 */
	public user(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		System.out.println(this.name+"~"+this.age);
		return super.toString();
	}
	
}
