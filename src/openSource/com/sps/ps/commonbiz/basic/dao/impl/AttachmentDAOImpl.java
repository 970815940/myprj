package com.sps.ps.commonbiz.basic.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.basic.dao.AttachmentIDAO;
import com.sps.ps.commonbiz.basic.entity.Attachment;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class AttachmentDAOImpl extends GeneralHibernateDAOImpl implements
		AttachmentIDAO {

	public int countAttachment(String hql, String[] values) {
		double rows=super.countByHql(hql, values);
		return (int)rows;
	}

	public void deleteAttachment(Attachment att) {
		super.Delete(att);

	}

	public Attachment getAttachmentById(String id) {
		Object obj=super.get(Attachment.class, id);
		return obj==null?null:(Attachment)obj;
	}

	public List getAttachmentByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void saveAttachment(Attachment att) {
		super.Save(att);

	}

	public void updateAttachment(Attachment att) {
		super.Update(att);

	}

}
