/*
 * Created on Jan 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.manpower.portal.utility.StringUtils;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseUIBean {
	private long id;
	private Date created_on; 
	private String created_by; 
	private Date updated_on; 
	private String updated_by; 
	private String xml_real_path;
	private String portletnamespace;
	private String showDirectReportsButton;
	
	protected Map incomingFields;
	
	
	
	protected void setUpdateModified(){	
		Date date = new Date();
		
		setUpdated_by("SYSTEM");
		setUpdated_on(date);
	}
	protected void setCreatedOn(){
		Date date = new Date();
		setCreated_by("SYSTEM");
		setCreated_on(date);
	}
	protected String getHtmlfromString(String htmlIn){
		
		if (htmlIn.indexOf("&lt;")>=0)htmlIn=StringUtils.replace(htmlIn,"&lt;","<");
		if (htmlIn.indexOf("&gt;")>=0)htmlIn=StringUtils.replace(htmlIn,"&gt;",">");
		if (htmlIn.indexOf("&quot;")>=0)htmlIn=StringUtils.replace(htmlIn,"&gt;","\"");
		if (htmlIn.indexOf("&apos;")>=0)htmlIn=StringUtils.replace(htmlIn,"&gt;","\'");
		
		return htmlIn;
	}
	
	protected void setPropreties(String formBase, Map incomingFields, Class classofBean){
		this.incomingFields=incomingFields;
//		Class classofBean = GemtSummaryReportUIBean.class;
		try{
			BeanInfo bi = Introspector.getBeanInfo(classofBean);
			MethodDescriptor[]mds = bi.getMethodDescriptors();
			for (int i = 0;i<mds.length;i++){
				MethodDescriptor md = mds[i];
				Method method = md.getMethod();
				//If the method is a set method of the class
				//process it.
				if (md.getName().indexOf("set")>-1){
					String propName = md.getName().substring(3,md.getName().length()).toLowerCase();
					Class [] clss = method.getParameterTypes();
					//Make sure the incoming Field is not null.
					if (incomingFields.get(formBase+propName)!=null){
						//Check to see if it's a date that needs to be set.
						if (clss[0].getName().indexOf("Date")>-1){
							//Parse the date
							if (notNullNotBlank(incomingFields.get(formBase+propName)))	{
								Locale locale = Locale.ENGLISH;
								DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", locale);
								Object [] objArgs = {formatter.parse((String)incomingFields.get(formBase+propName))};
								method.invoke(this,objArgs);
							}
						}else if (clss[0].getName().indexOf("long")>-1){
							if (notNullNotBlank(incomingFields.get(formBase+propName)))	{
								String tempLong = (String)incomingFields.get(formBase+propName);
								Object [] objArgs = {new Long(tempLong)};
								method.invoke(this,objArgs);
							}
						}else{
							//Set the value with the method.
							Object [] objArgs = {incomingFields.get(formBase+propName)};
							method.invoke(this,objArgs);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private boolean notNullNotBlank(Object obj){
		boolean test = obj!=null;
		test = !((String)obj).trim().equals("");
		return test;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the created_by.
	 */
	public String getCreated_by() {
		return created_by;
	}
	/**
	 * @param created_by The created_by to set.
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	/**
	 * @return Returns the created_on.
	 */
	public Date getCreated_on() {
		return created_on;
	}
	/**
	 * @param created_on The created_on to set.
	 */
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
	/**
	 * @return Returns the updated_by.
	 */
	public String getUpdated_by() {
		return updated_by;
	}
	/**
	 * @param updated_by The updated_by to set.
	 */
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	/**
	 * @return Returns the updated_on.
	 */
	public Date getUpdated_on() {
		return updated_on;
	}
	/**
	 * @param updated_on The updated_on to set.
	 */
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public String getXml_real_path() {
		return xml_real_path;
	}

	public void setXml_real_path(String xml_real_path) {
		this.xml_real_path = xml_real_path;
	}

	public String getPortletnamespace() {
		return portletnamespace;
	}

	public void setPortletnamespace(String portletnamespace) {
		this.portletnamespace = portletnamespace;
	}
	public String getShowDirectReportsButton() {
		return showDirectReportsButton;
	}
	public void setShowDirectReportsButton(String showDirectReportsButton) {
		this.showDirectReportsButton = showDirectReportsButton;
	}
	
	
}
