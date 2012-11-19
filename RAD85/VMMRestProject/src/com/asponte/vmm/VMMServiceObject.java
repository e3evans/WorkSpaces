package com.asponte.vmm;

import com.ibm.websphere.wim.Service;
import com.ibm.websphere.wim.client.LocalServiceProvider;

public class VMMServiceObject {
	
	 private static VMMServiceObject vobj=null;
	 private static Service service;
	 private VMMServiceObject(){
	 }
	 
	 public static VMMServiceObject createVMMServiceObject(){
		 if (vobj==null){
	    		try { 
	                // Local access virtual member manager Service
	    			vobj=new VMMServiceObject();
	    			service = new LocalServiceProvider(null);
	    			return vobj;
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	    	}
		 
		 
		 return vobj;
		 
	 }


	 public Service getLocalServiceProvider(){
		 
		 return service;
		 
	 }
	

}
