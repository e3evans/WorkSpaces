package com.manpower.directtalentseo.utility;

import java.util.ResourceBundle;

public abstract class PageUtils {
	
	public static String checkforNulls(Object incoming){
		
		if (incoming == null){
			ResourceBundle rb = ResourceBundle.getBundle("manpower.translations.JobCategory");
			return rb.getString("notprovided");
		}
		
		return incoming.toString();
		
	}

}
