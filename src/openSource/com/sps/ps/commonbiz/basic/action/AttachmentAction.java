/*
 * 
 */
package com.sps.ps.commonbiz.basic.action;

import com.sps.ps.commonbiz.basic.service.AttachmentService;
import com.sps.ps.core.web.impl.SysWebCtrlAction;


/**
 *<p>ClassName: AttachmentAction</p>
 *<p>Description: </p> 
 *<p>Company ：www.nice1234.com</p> 
 * @author   <a href="">taoxs</a>
 * @date Oct 12, 2013
 * @version  1.0
 */
public class AttachmentAction extends SysWebCtrlAction {

	/** 
	 *serialVersionUID
	 */  
	
	private static final long serialVersionUID = -6320172917461828647L;
	private AttachmentService attachmentServiceImpl;
	/**
	 * @param attachmentServiceImpl the attachmentServiceImpl to set
	 */
	public void setAttachmentServiceImpl(AttachmentService attachmentServiceImpl) {
		this.attachmentServiceImpl = attachmentServiceImpl;
	}
	/**
	 *<p>Title: upload</p>
	 *<p>Description:上传附件 </p> 
	 * @return
	 */
	public String upload(){
		return super.UPLOAD_ATT_SUCCESS;
	}
	/**
	 *<p>Title: download</p>
	 *<p>Description:下载附件 </p> 
	 * @return
	 */
	public String download(){
		
		return super.DOWNLOAD_ATT_SUCCESS;
	}
	/**
	 *<p>Title: findAttList</p>
	 *<p>Description:获取附件列表 </p> 
	 * @return
	 */
	public String findAttList(){
		return super.FIND_BY_OBJLIST_SUCCESS;
	}
	
}
