package com.manpower.portal.gemt.ui.services;

import java.util.List;

import com.manpower.portal.gemt.ui.beans.GemtManagersUIBean;

public interface GemtManagersService {

	public List findAll();
	public GemtManagersUIBean findByEmail(String eMail);
	public GemtManagersUIBean AddManager(GemtManagersUIBean gemtUI);
}
