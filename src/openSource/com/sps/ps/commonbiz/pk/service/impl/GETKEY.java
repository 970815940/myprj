package com.sps.ps.commonbiz.pk.service.impl;

import com.sps.ps.commonbiz.pk.service.Generate;
import com.sps.ps.core.bean.BeanContextHolders;

public  class GETKEY {
	private static Generate generateImpl; 
	/**
	 * @param generateImpl the generateImpl to set
	 */
	public void setGenerateImpl(Generate generateImpl) {
		this.generateImpl = generateImpl;
	}
	/**
	 * 初始化GETKEY类 注入generateImpl属性值
	 */
	public static void initGetKey(){
		//
		generateImpl=(Generate) BeanContextHolders.getBean("com.sps.ps.commonbiz.pk.generateImpl");
	}
	/**
	 * 根据CODE编码规则 生成一个新增的String字符串
	 * @param code
	 * @return
	 */
	public static String getKey(String code){
		initGetKey();
		String key=null;
		if(code==null||code.equals("")){
			key=generateImpl.getkey();
		}else{
			key=generateImpl.getKey(code);
		}
		return key;
	}
	public static String getKey(){
		return getKey(null);
	}
}
