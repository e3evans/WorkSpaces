package com.manpower.sss.portlet.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.apache.log4j.Logger;

import pagecode.PageCodeBase;

public class Config extends PageCodeBase{

	private PortletPreferences preferences;
	private static Logger log = Logger.getLogger(Config.class);
	private static final String WS_PORT_CONST = "WEB_SERVICE_CONNECT_PORT";
	
	private String port;
	
	public Config()
	{
		if(preferences == null){
			PortletRequest request=(PortletRequest)facesContext.getExternalContext().getRequest();
			preferences = request.getPreferences();
		}
		
		init();
		
		Enumeration enums = preferences.getNames();
		log.debug("<----------------------->");
		log.debug("PREFERENCES:");
		String name = null;
		Object value = null;
		while(enums.hasMoreElements()){
			name  = (String)enums.nextElement();
			value = preferences.getMap().get(name);
			
			log.debug("key [" + name + "] = " + value.toString() + ", class: " + value.getClass().getName());
		}
		
		log.debug("<----------------------->");
	}
	
	public String getCountryBundleName()
	{
		String countryBundleName="application.messages.nl.USA_ApplicationMessages";
		
		return countryBundleName;
	}
	
	private void init()
	{
		port = preferences.getValue(WS_PORT_CONST, "10000");
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void doSave(ActionEvent event)
	{
		if(preferences == null){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			PortletRequest request=(PortletRequest)facesContext.getExternalContext().getRequest();
			preferences = request.getPreferences();
		}
		
		try {
			preferences.setValue(WS_PORT_CONST, getPort());
			
			preferences.store();
		} catch (ReadOnlyException e) {
			
			log.error("Exception", e);
		} catch (ValidatorException e) {
			
			log.error("Exception", e);
		} catch (IOException e) {
			
			log.error("Exception", e);
		}
		
	}
	
	public String save()
	{
		return "";
	}
	
	
}
