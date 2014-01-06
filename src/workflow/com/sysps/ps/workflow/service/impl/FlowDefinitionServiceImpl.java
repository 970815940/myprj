/* FlowDefinitionServiceImpl.java 
 * Dec 26, 2013  10:09:56 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.service.impl;

import java.awt.image.BufferedImage;
import java.util.zip.ZipInputStream;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.ProcessDefinition;
import org.springframework.util.Assert;

import com.sps.ps.utils.StringUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sysps.ps.workflow.service.FlowDefinitionService;

/**
 *<p>FlowDefinitionServiceImpl</p>
 *<p></p> 
 *<p></p> 
 * @author  <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 26, 2013 10:09:56 AM
 * @version  
 */
public class FlowDefinitionServiceImpl implements FlowDefinitionService {
	private JbpmConfiguration jbpmConfig;
	/**
	 * @param jbpmConfig the jbpmConfig to set
	 */
	public void setJbpmConfig(JbpmConfiguration jbpmConfig) {
		this.jbpmConfig = jbpmConfig;
	}
	public void deployDefinition(ZipInputStream zis) {
		Assert.isTrue(zis!=null, "ZipInputStream is not null!");
		JbpmContext context=null;
		try {
			context=jbpmConfig.getCurrentJbpmContext();
			if(context==null)context=jbpmConfig.createJbpmContext();
			ProcessDefinition pd=ProcessDefinition.parseParZipInputStream(zis);
			context.deployProcessDefinition(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//出现异常 则回滚事务
			context.getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			if(context!=null)context.close();
		}
	}
	public byte[] getFlowDefinitionGrapImg(String id) {
		JbpmContext context=null;
		BufferedImage image= null;
		byte[] b=null;
		try {
			Assert.isTrue(StringUtil.isEmpty(id),"流程定义ID不能为空");
			context=jbpmConfig.getCurrentJbpmContext();
			if(context==null)context=jbpmConfig.createJbpmContext();
			ProcessDefinition pd=context.getGraphSession().getProcessDefinition(Long.parseLong(id));
			FileDefinition fd=pd.getFileDefinition();
			b=fd.getBytes("processimage.jpg");
			image=new BufferedImage(400,400,BufferedImage.TYPE_3BYTE_BGR);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(context!=null)context.close();
		}
		return b;
	}
	public void deleteFlowDefinition(String id) {
		JbpmContext context=null;
		try {
			context=jbpmConfig.getCurrentJbpmContext();
			if(context==null)context=jbpmConfig.createJbpmContext();
			long pdId=Long.parseLong(id);
			context.getGraphSession().deleteProcessDefinition(pdId);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.getSession().getTransaction().rollback();
		}finally{
			if(context!=null)context.close();
		}
		
	}

}
    
