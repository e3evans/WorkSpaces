package com.manpower.portal.utility;

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
        
        String fileName=servletContext.getInitParameter("EMAIL_CONFIG_FILENAME");

        fileName = servletContext.getRealPath(fileName);
        fileName = fileName.replace('\\', '/').replace('/',
                File.separatorChar);
        
        EmailConfig.getInstance(fileName);
    }

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)
    {
  

    }

}