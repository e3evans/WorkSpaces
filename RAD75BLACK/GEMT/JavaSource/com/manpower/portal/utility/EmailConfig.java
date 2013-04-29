package com.manpower.portal.utility;

import java.util.*;
import java.io.*;

public class EmailConfig
{
    private static EmailConfig instance;

    protected boolean loaded;

    protected String filename;

    private Properties p;

    protected String defConfigFilename;

    private EmailConfig(String fileName)
    {
        loaded = false;
        p = new Properties();

        this.filename = fileName;
        
        loadDefault();
    }

    public static synchronized EmailConfig getInstance(String fileName)
    {
        if (instance == null)
        {
            instance = new EmailConfig(fileName);
        }

        return instance;
    }

    public synchronized void setConfigFile(File configFile) throws IOException
    {
        if (!configFile.exists())
        {
            configFile.createNewFile();
        }
        FileInputStream fin = new FileInputStream(configFile);
        if (fin == null)
            return;
        p.load(fin);

        loaded = true;
    }

    public synchronized void set(String key, String value)
    {
        p.put(key, value);
    }

    private synchronized String get(String key)
    {
        String res = get(key, null);
        if (res != null)
            res = res.trim();

        return res;
    }

    private synchronized String get(String key, String defaultValue)
    {
        String res = p.getProperty(key, defaultValue);
        if (res != null)
            res = res.trim();

        return res;
    }

//    private synchronized boolean getBoolean(String key, String defaultValue)
//    {
//        return ((get(key, defaultValue)).equalsIgnoreCase("true"));
//    }
//
//    private synchronized int getInt(String key, String defaultValue)
//    {
//        int res = -1;
//        String tmp = (get(key, defaultValue));
//        try
//        {
//            res = Integer.parseInt(tmp);
//        }
//        catch (Exception e)
//        {
//        }
//
//        return res;
//    }
//
//    private synchronized long getLong(String key, String defaultValue)
//    {
//        long res = -1;
//        String tmp = (get(key, defaultValue));
//        try
//        {
//            res = Long.parseLong(tmp);
//        }
//        catch (Exception e)
//        {
//        }
//
//        return res;
//    }

    private void loadDefault()
    {
        try
        {
            if (filename == null)
            {
                filename = System.getProperty("configFile");
                if (filename != null)
                {
                    System.out
                            .println("Using emailConfigFile property: " + filename);
                }
                else
                {
                    filename = new String(defConfigFilename);
                }
            }

            setConfigFile(new File(filename));

        }
        catch (IOException ioe)
        {
            System.out.println("Loading emailConfigFile: " + filename + " "
                    + ioe.getMessage());
        }
    }

    public static String getEmailHost()
    {
        return instance.get("email_host");
    }

    public static String getEmailUser()
    {
    	return instance.get("email_user");
    }

    public static String getEmailPassword()
    {
        return instance.get("email_password");
    }
}
