package com.sps.ps.commonbiz.org.service;

import java.util.List;

import com.sps.ps.commonbiz.org.entity.SysUser;

public interface SysUserService {
	public void addSysUser(SysUser user);
	public void deleteSysUser(SysUser user);
	public void updateSysUser(SysUser user);
	public SysUser getSysUserById(String id);
	public List getSysUserByList(String[] columns,String[] ysf,String[] values,int start,int size);
	public int countSysUser(String[] columns,String[] ysf,String[] values);
	public SysUser getSysUserByLoginId(String loginId);
}
