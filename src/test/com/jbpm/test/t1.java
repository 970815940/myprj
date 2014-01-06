/* t1.java 
 * Dec 23, 2013  9:34:36 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.jbpm.test;

import org.hibernate.annotations.AccessType;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

import junit.framework.TestCase;

/**
 *<p>t1</p>
 *<p></p> 
 *<p></p> 
 * @author  <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 23, 2013 9:34:36 AM
 * @version  
 */
public class t1 extends TestCase {
		JbpmConfiguration jbpmConfig=null;
		JbpmContext context=null;
		public void test1(){
			try {
				init();
				ProcessDefinition pd=ProcessDefinition.parseXmlResource("processdefinition.xml");
				context.deployProcessDefinition(pd);
				context.close();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		void init(){
			jbpmConfig =JbpmConfiguration.getInstance();
			context=jbpmConfig.createJbpmContext();
		}
}
    	
