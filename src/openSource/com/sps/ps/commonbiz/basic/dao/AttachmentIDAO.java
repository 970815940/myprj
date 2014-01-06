package com.sps.ps.commonbiz.basic.dao;

import java.util.List;

import com.sps.ps.commonbiz.basic.entity.Attachment;

public interface AttachmentIDAO {
	public void saveAttachment(Attachment att);
	public void deleteAttachment(Attachment att);
	public void updateAttachment(Attachment att);
	public Attachment getAttachmentById(String id);
	public List getAttachmentByList(String hql,String[] values,int start,int size);
	public int countAttachment(String hql,String[] values);
}
