package com.manpower.hellostruts2portlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.manpower.hellostruts2portlet.domain.Bookmark;

import javax.portlet.PortletPreferences;

import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.apache.struts2.portlet.interceptor.PortletPreferencesAware;

public class ListBookmarksAction extends DefaultActionSupport implements
		PortletPreferencesAware {

	private static final long serialVersionUID = -903810280074836990L;
	private List <Bookmark> bookmarks = new ArrayList<Bookmark>();
	private PortletPreferences portletPreferences;
	
	private String testme = "testme!!";
	
	
	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setPortletPreferences(PortletPreferences portletPreferences) {
		this.portletPreferences=portletPreferences;
	}

	@Override
	public String execute() throws Exception {
		// For simplicity, we'll assume that only bookmarks are stored in the preferences.
	      Map<String, String[]> preferencesMap = portletPreferences.getMap();
	      for(Map.Entry<String, String[]> entry : preferencesMap.entrySet()) {
	         bookmarks.add(new Bookmark(entry.getKey(), entry.getValue()[0]));
	      }
	      return SUCCESS;
	}

	public String getTestme() {
		return testme;
	}
	
	

}
