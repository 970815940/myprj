package com.sps.ps.commonbiz.basic.service;

import java.util.List;

import com.sps.ps.commonbiz.basic.entity.Attachment;

public interface AttachmentService {
	public void saveAttachment(Attachment att);
	public void deleteAttachment(Attachment att);
	public void updateAttachment(Attachment att);
	public Attachment getAttachmentById(String id);
	public List getAttachmentByList(String[] values,int start,int size);
	public int countAttachment(String[] values);
	/**
	 *<p>Title: findAttachmentBymodel</p>
	 *<p>Description: 根据model获取附件</p> 
	 * @param model
	 * @return
	 */
	public List findAttachmentBymodel(String model);
	/**
	 *<p>Title: findAttachmentByBusinessId</p>
	 *<p>Description: 根据业务ID获取附件信息</p> 
	 * @param bizId
	 * @return
	 */
	public List findAttachmentByBusinessId(String bizId);
	/**
	 *<p>Title: findAttachmentByModelBusinessId</p>
	 *<p>Description: 根据model ,业务ID 获取附件信息</p> 
	 * @param model
	 * @param bizId
	 * @return
	 */
	public List findAttachmentByModelBusinessId(String model,String bizId);
}
