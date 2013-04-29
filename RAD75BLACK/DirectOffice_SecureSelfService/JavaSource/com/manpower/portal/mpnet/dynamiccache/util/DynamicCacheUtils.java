package com.manpower.portal.mpnet.dynamiccache.util;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpSession;

import com.ibm.portal.portlet.service.PortletServiceHome;
//import com.manpower.portal.cache.services.CachedMapPortletService;
//import com.manpower.portal.cache.services.CachedMapPortletServiceImpl;

public class DynamicCacheUtils {

	
    /*private static PortletServiceHome dynamicMap = null;
    
	static
	{
      	try {
              Context ctx = new InitialContext();
              Object home = ctx.lookup("portletservice/com.manpower.portal.cache.services.CachedMapPortletService");
              if (home != null) {
              	dynamicMap = (PortletServiceHome) home;
              }
          } catch (Exception ex) {
             ex.printStackTrace();
          }
    }
    
    public static void setDataInDynamicCache(String key, Object value,PortletSession session){
    	 try {
    		 if (dynamicMap != null){
    			
    			 CachedMapPortletServiceImpl service = (CachedMapPortletServiceImpl)dynamicMap.getPortletService(CachedMapPortletService.class);
		         service.setSessionData(key, value, session);
		         System.out.println("<DYNAMIC_CACHE> Setting key '" + key + "' = '" + value + "' .");
    		 }
    		 else
    		 {
    			 System.out.println("<Dynamic map is null> (SET)");
    		 }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
    }
    
    public static Object getDataFromDynamicCache(String key,PortletSession session){
    	Object value = null;
   	 	try {
	      	if (dynamicMap != null)
	          {
	          	CachedMapPortletServiceImpl service = (CachedMapPortletServiceImpl)dynamicMap.getPortletService(CachedMapPortletService.class);
	          	value = service.getSessionData(key, session);
	          	System.out.println("<DYNAMIC_CACHE> Getting key '" + key + "'. Value is '" + value + "'");
	          	
	          }else{
	          	System.out.println("<Dynamic map is null> (GET)");
	          }
   	 	} catch (Exception e) {
   	 		e.printStackTrace();
        }
      
      return value;
    }
    
    public static Object getDataAndRemoveItFromDynamicCache(String key,PortletSession session){
       	Object value = null;
   	 	try {
	      	if (dynamicMap != null)
	          {
	          	CachedMapPortletServiceImpl service = (CachedMapPortletServiceImpl)dynamicMap.getPortletService(CachedMapPortletService.class);
	          	value = service.getSessionAndRemoveData(key, session);
	          	System.out.println("<DYNAMIC_CACHE> Getting and removing key '" + key + "'. Value is '" + value + "'");
	          	
	          }else{
	          	System.out.println("<Dynamic map is null> (GET AND REMOVE)");
	          }
   	 	} catch (Exception e) {
   	 		e.printStackTrace();
        }
      
      return value;
    }
	
    
    public static void setDataInDynamicCache(String key, Object value,HttpSession session){
   	 try {
   		 if (dynamicMap != null){
   			
   			 CachedMapPortletServiceImpl service = (CachedMapPortletServiceImpl)dynamicMap.getPortletService(CachedMapPortletService.class);
		         service.setSessionData(key, value, session);
		         System.out.println("<DYNAMIC_CACHE> Setting key '" + key + "' = '" + value + "' .");
   		 }
   		 else
   		 {
   			 System.out.println("<Dynamic map is null> (SET)");
   		 }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
   }    
    
    public static Object getDataFromDynamicCache(String key, HttpSession session) {
    	Object value = null;
   	 	try {
	      	if (dynamicMap != null)
	          {
	          	CachedMapPortletServiceImpl service = (CachedMapPortletServiceImpl)dynamicMap.getPortletService(CachedMapPortletService.class);
	          	value = service.getSessionData(key, session);
	          	System.out.println("<DYNAMIC_CACHE> Getting key '" + key + "'. Value is '" + value + "'");
	          	
	          }else{
	          	System.out.println("<Dynamic map is null> (GET)");
	          }
   	 	} catch (Exception e) {
   	 		e.printStackTrace();
        }
      
      return value;
    }
    
    
*/    
        
}
