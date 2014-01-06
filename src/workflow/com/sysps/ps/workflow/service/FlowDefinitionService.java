/* FlowDefinitionService.java 
 * Dec 26, 2013  10:09:42 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.service;

import java.awt.image.BufferedImage;
import java.util.zip.ZipInputStream;

/**
 *<p>FlowDefinitionService</p>
 *<p></p> 
 *<p></p> 
 * @author  <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 26, 2013 10:09:42 AM
 * @version  
 */
public interface FlowDefinitionService {
	public void deployDefinition(ZipInputStream zis);
	public byte[] getFlowDefinitionGrapImg(String id);
	public void deleteFlowDefinition(String id);
}
    
