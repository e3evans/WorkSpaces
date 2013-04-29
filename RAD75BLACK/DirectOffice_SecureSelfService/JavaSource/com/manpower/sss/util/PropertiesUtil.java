package com.manpower.sss.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class PropertiesUtil {
	
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static PropertiesUtil instance;
	
	private Properties properties;
	private String fileName;
	
	public static synchronized PropertiesUtil getInstance(String configFileName)
	{
		if(instance == null)
		{
			instance = new PropertiesUtil(configFileName);
		}
		
		return instance;
	}
	
	
	private PropertiesUtil(String configFileName)
	{
		this.fileName = configFileName;
		loadProperties();
	}
	
	private void loadProperties()
	{
		properties = new Properties();
	
		try{
	
			File file = new File(getConfigurationFileName());
			
			FileInputStream fis = new FileInputStream(file);
		
			properties.load(fis);
		
		}catch(Exception ex){
			if(logger.isEnabledFor(Level.ERROR)) {
				logger.error("FAILED to read properties.", ex);
			}
		}
	}
	
	protected String getConfigurationFileName(){		
		
		return fileName;
	}
	
	private synchronized String get(String key, String defaultValue)
    {
        String res = properties.getProperty(key, defaultValue);
        if (res != null)
            res = res.trim();

        return res;
    }
	
	private synchronized String get(String key)
    {
        String res = get(key, null);
        if (res != null)
            res = res.trim();

        return res;
    }
	
	public static String getStringValue(String key)
    {
        String res = instance.get(key, null);
        if (res != null)
            res = res.trim();

        return res;
    }
	
	public static List getPropertyValuesAsList(String propertyValue)
	{
		List result = new ArrayList();
		String value = instance.get(propertyValue);
		if(value != null)
		{
			String[] valuesArray = value.split(",");
			
			for(int i = 0; i < valuesArray.length; i++)
			{
				result.add(valuesArray[i]);
			}
		}
		return result;
	}
}
