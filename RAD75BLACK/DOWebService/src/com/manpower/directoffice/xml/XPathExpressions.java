package com.manpower.directoffice.xml;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class XPathExpressions
{
	private static final String BUNDLE_NAME = "conf.injection_xpath_expressions";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	private XPathExpressions()
	{
	}
	
	public static String getString(String key)
	{
		try
		{
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}
}
