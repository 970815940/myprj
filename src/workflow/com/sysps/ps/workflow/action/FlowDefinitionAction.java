/* FlowDefinitionAction.java 
 * Dec 26, 2013  9:54:07 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import com.sps.ps.core.web.impl.SysWebCtrlAction;
import com.sps.ps.utils.entity.SimpleMsg;
import com.sps.ps.utils.entity.TableMsg;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sysps.ps.workflow.service.FlowDefinitionService;

/**
 *<p>FlowDefinitionAction</p>
 *<p></p> 
 *<p></p> 
 * @author  <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 26, 2013 9:54:07 AM
 * @version  
 */
public class FlowDefinitionAction extends SysWebCtrlAction {

	/** 
	 *serialVersionUID
	 */  
	private static final long serialVersionUID = 1L;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String pdId;
	////service
	private FlowDefinitionService definitionServiceImpl;
	
	/**
	 * @param definitionServiceImpl the definitionServiceImpl to set
	 */
	public void setDefinitionServiceImpl(FlowDefinitionService definitionServiceImpl) {
		this.definitionServiceImpl = definitionServiceImpl;
	}

	public String uploadFlowDefinition(){
		try {
			if(file!=null){
				InputStream fileIs=null;
				ZipInputStream zis=null;
				try {
					fileIs=new FileInputStream(file);
					zis=new ZipInputStream(fileIs);
					
					definitionServiceImpl.deployDefinition(zis);
					
					super.smsg=new SimpleMsg("",true,null);
					
					return super.UPLOAD_ATT_SUCCESS;
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					fileIs.close();
					zis.close();
					if(file!=null)file.delete();
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.smsg=new SimpleMsg("",false,null);
		return super.ERROR;
		
	}
	public void getFlowImg(){
		try {
			HttpServletResponse response=this.clientProxy.getResponse();
			response.setContentType("image/jpeg");   
			byte[] image=this.definitionServiceImpl.getFlowDefinitionGrapImg(pdId);
			OutputStream os= response.getOutputStream();
	        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
	        //encoder.encode(image);
	        os.write(image);
			os.flush();
	        os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String deleteFlowDefinition(){
		
		try {
			this.definitionServiceImpl.deleteFlowDefinition(pdId);
			super.smsg=new SimpleMsg("",true,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.smsg=new SimpleMsg(e.getMessage(),true,null);
		}
		
		return super.AJAX_SUCCESS;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the fileContentType
	 */
	public String getFileContentType() {
		return fileContentType;
	}

	/**
	 * @param fileContentType the fileContentType to set
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * @param pdId the pdId to set
	 */
	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

}
    
