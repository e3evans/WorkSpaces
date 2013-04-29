/*
 * Created on Nov 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;




/**
 * @author Dmitry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HibernateResourseLocator {

	private static HibernateResourseLocator ref=null;
	private static Properties prop=null;

	public static synchronized HibernateResourseLocator getInstance() {
		if (ref==null) {
				ref=new HibernateResourseLocator();
			}

		return ref;
	}
	private HibernateResourseLocator() {
		System.out.println("Entering singleton constructor");
		try {
			 ClassLoader classLoader =
			 	Thread.currentThread().getContextClassLoader();
		InputStream is=classLoader.getResourceAsStream(HibernateConstants.HIBERNATE_QUERY_PROPERTIES);
		if (is!=null) {
			prop = new Properties();
			prop.load(is);
		}
		} catch (IOException e) {
			prop=null;
			e.printStackTrace();
		} finally {
			if (prop==null) {
				throw new IllegalArgumentException ("could not load "+HibernateConstants.HIBERNATE_QUERY_PROPERTIES);
			}
		}
	}
	public String getValue(String key) {
		return (String) prop.get(key);
	}
}
