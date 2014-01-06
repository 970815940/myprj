package com.sps.ps.core.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * bean上下文持有类
 * @author Administrator
 *
 */
public class BeanContextHolders implements ApplicationContextAware {
	 final static BeanContextHolders bch=new BeanContextHolders();
	 private static ApplicationContext act;
	 
	private BeanContextHolders(){}//单例模式
	public static ApplicationContext getAppContext(){
		return act;
	}
	public static Object getBean(String id){
		return act.getBean(id);
	}
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.act=arg0;
	}
}
