package com.sps.ps.commonbiz.basic.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.basic.dao.AttachmentIDAO;
import com.sps.ps.commonbiz.basic.entity.Attachment;
import com.sps.ps.commonbiz.basic.service.AttachmentService;

public class AttachmentServiceImpl implements AttachmentService {
	private AttachmentIDAO attachmentDAOImpl;
	
	/**
	 *<p>Title: setAttachmentDAOImpl</p>
	 *<p>Description:Spring  </p> 
	 * @param attachmentDAOImpl
	 */
	public void setAttachmentDAOImpl(AttachmentIDAO attachmentDAOImpl) {
		this.attachmentDAOImpl = attachmentDAOImpl;
	}

	public int countAttachment(String[] values) {
		StringBuffer hql=new StringBuffer();
		
		return this.attachmentDAOImpl.countAttachment(hql.toString(), values);
	}

	public void deleteAttachment(Attachment att) {
		this.attachmentDAOImpl.deleteAttachment(att);

	}

	/* (non-Javadoc)
	 *<p>Title: findAttachmentByBusinessId</p> 
	 *<p>Description: </p>  
	 * @see com.sps.ps.commonbiz.basic.service.AttachmentService#findAttachmentByBusinessId(java.lang.String)
	 * @param bizId
	 * @return 
	 */
	public List findAttachmentByBusinessId(String bizId) {
		StringBuffer hql=new StringBuffer("from Attachment where businessid='"+bizId+"'");
		return this.attachmentDAOImpl.getAttachmentByList(hql.toString(), null, -1, -1);
	}

	/* (non-Javadoc)
	 *<p>Title: findAttachmentByModelBusinessId</p> 
	 *<p>Description: </p>  
	 * @see com.sps.ps.commonbiz.basic.service.AttachmentService#findAttachmentByModelBusinessId(java.lang.String, java.lang.String)
	 * @param model
	 * @param bizId
	 * @return 
	 */
	public List findAttachmentByModelBusinessId(String model, String bizId) {
		StringBuffer hql=new StringBuffer("from Attachment where businessid='"+model+"' and model='"+model+"'");
		return this.attachmentDAOImpl.getAttachmentByList(hql.toString(), null, -1, -1);
	}

	/* (non-Javadoc)
	 *<p>Title: findAttachmentBymodel</p> 
	 *<p>Description: </p>  
	 * @see com.sps.ps.commonbiz.basic.service.AttachmentService#findAttachmentBymodel(java.lang.String)
	 * @param model
	 * @return 
	 */
	public List findAttachmentBymodel(String model) {
		StringBuffer hql=new StringBuffer("from Attachment where model= '"+model+"'");
		return this.attachmentDAOImpl.getAttachmentByList(hql.toString(),null, -1, -1);
	}

	public Attachment getAttachmentById(String id) {
		
		return this.attachmentDAOImpl.getAttachmentById(id);
	}

	public List getAttachmentByList(String[] values, int start, int size) {
		StringBuffer hql=new StringBuffer("from Attachment");
		return this.attachmentDAOImpl.getAttachmentByList(hql.toString(), values, start, size);
	}

	public void saveAttachment(Attachment att) {
		this.attachmentDAOImpl.saveAttachment(att);

	}

	public void updateAttachment(Attachment att) {
		this.attachmentDAOImpl.updateAttachment(att);

	}

}
