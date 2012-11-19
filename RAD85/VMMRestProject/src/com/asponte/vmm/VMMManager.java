package com.asponte.vmm;

import com.ibm.websphere.wim.SchemaConstants;
import com.ibm.websphere.wim.Service;
import com.ibm.websphere.wim.util.SDOHelper;
import commonj.sdo.DataObject;


public class VMMManager extends VMMBase{
//  DataObject result = (DataObject) runAsUser(user, password, new java.security.PrivilegedExceptionAction()
	
	private static Service service;

	public static String searchVMM(String userName,String password,final String searchBase,final String searchTerm) throws Exception{
		VMMServiceObject vobj = VMMServiceObject.createVMMServiceObject();
		service = vobj.getLocalServiceProvider();

		@SuppressWarnings("rawtypes")
//		DataObject result = (DataObject) runAsUser("waslocal", "waslocal", new java.security.PrivilegedExceptionAction()
		DataObject result = (DataObject) runAsUser(userName, password, new java.security.PrivilegedExceptionAction()
		{
	        @SuppressWarnings("unchecked")
			public Object run() throws Exception
	        {
	            //Note the service instance used is that of security domain obtained in step 1.
	            DataObject root = service.createRootDataObject();
	            DataObject searchCtrl = SDOHelper.createControlDataObject(root, null, SchemaConstants.DO_SEARCH_CONTROL);
	            // Set the properties that need to be retrieved in search results
	            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("uid");
	            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("samAccountName"); 
	            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("userprincipalname"); 
	            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("groupTypes");  
	            searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("ibm-jobTitle");
	            // Set the search base in which search will be performed
//	            searchCtrl.getList(SchemaConstants.PROP_SEARCH_BASES).add("o=defaultWIMFileBasedRealm");
	            String searchString="";
	            if (!searchTerm.equals("")){
	            	searchString=searchTerm+"*";
	            }
	            searchCtrl.getList(SchemaConstants.PROP_SEARCH_BASES).add(searchBase);
	            // Set the search filter as uid, here the expression will search for all uids
//	            searchCtrl.setString(SchemaConstants.PROP_SEARCH_EXPRESSION, "@xsi:type='PersonAccount' and uid='"+searchString+"'");
	            searchCtrl.setString(SchemaConstants.PROP_SEARCH_EXPRESSION, "@xsi:type='PersonAccount' and cn='"+searchString+"'");
	            //	            System.out.println("Input datagraph before searching for users in the searchbase"+ printDO(root));  
	            DataObject retObject = service.search(root);
	            return retObject;
	        }
	    });
		return printDO(result).trim();
	}
	
    
    
}
