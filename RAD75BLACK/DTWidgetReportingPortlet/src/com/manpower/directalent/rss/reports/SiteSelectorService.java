package com.manpower.directalent.rss.reports;

public interface SiteSelectorService {

	public String getSiteSelector(String namespace);
	public Object [] getSites();
	public Object getDefaultItem();
}
