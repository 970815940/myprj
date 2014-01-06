package com.sps.ps.commonbiz.security.action;

import com.sps.ps.commonbiz.security.service.ModelRightService;
import com.sps.ps.core.web.impl.SysWebCtrlAction;

public class ModelRightAction extends SysWebCtrlAction  {
	private ModelRightService modelRightServiceImpl;

	public void setModelRightServiceImpl(ModelRightService modelRightServiceImpl) {
		this.modelRightServiceImpl = modelRightServiceImpl;
	}
}
