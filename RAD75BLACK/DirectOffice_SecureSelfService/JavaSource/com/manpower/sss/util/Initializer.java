package com.manpower.sss.util;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class Initializer implements ServletContextListener
{
	/**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)
    {
    	ServletContext servletContext=event.getServletContext();
    	String configFileName=servletContext.getInitParameter("CONFIG_FILENAME");
    	configFileName = servletContext.getRealPath(configFileName);
        configFileName = configFileName.replace('\\', '/').replace('/',
                File.separatorChar);
        
        PropertiesUtil.getInstance(configFileName);
    }
    
    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)
    {
  

    }
}
