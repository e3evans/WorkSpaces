package com.manpower.directtalent.rss.ui.service;

import java.util.List;

import com.manpower.directtalent.rss.ui.beans.RptResultsUIBean;

public interface RptResultsService {
	public List findAll();
	public RptResultsUIBean findByID(Long id);
}
