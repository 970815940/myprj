package com.sps.ps.finance.hibernateListeners;


import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.event.DeleteEvent;
import org.hibernate.event.DeleteEventListener;
import org.hibernate.event.EventSource;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

/**
 *<p>Title: DeleteListeners</p>
 *<p>Description: </p> 
 *<p>Company: Sysway</p> 
 * @author   <a href="">taoxs</a>
 * @date Oct 12, 2013
 * @version
 */
public class DeleteListeners implements DeleteEventListener {



	/** 
	 *serialVersionUID
	 */  
	
	private static final long serialVersionUID = 1L;

	public void onDelete(DeleteEvent arg0) throws HibernateException {
		
		
	}

	public void onDelete(DeleteEvent arg0, Set arg1) throws HibernateException {
		
		
	}
	
}
