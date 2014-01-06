package com.sps.ps.finance.hibernateListeners;

import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;

import com.sps.ps.finance.entity.Finance;
import com.sps.ps.utils.ArrayUtil;

/**
 *<p>Title: InserListeners</p>
 *<p>Description: 监听器 ,</p> 
 *<p>Company: Sysway</p> 
 * @author   <a href="">taoxs</a>
 * @date Oct 12, 2013
 * @version  
 */
public class InserListeners implements PostInsertEventListener {
	
	public void onPostInsert(PostInsertEvent ev) {
		Object obj=ev.getEntity();
		if(obj instanceof Finance){
			Finance f=(Finance)obj;
			System.out.println(ev.getId());
			System.out.println(f.getFReadme());
			
			System.out.println(ArrayUtil.toString(ev.getPersister().getPropertyNames()));
		}else{
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

}
