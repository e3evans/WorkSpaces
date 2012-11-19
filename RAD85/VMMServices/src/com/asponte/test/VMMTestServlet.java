package com.asponte.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Hashtable;
import java.util.List;

import com.ibm.websphere.wim.SchemaConstants;
import com.ibm.websphere.wim.Service;
import com.ibm.websphere.wim.client.LocalServiceProvider;
import com.ibm.websphere.wim.ras.WIMTraceHelper;
import com.ibm.websphere.wim.util.SDOHelper;

import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import com.ibm.websphere.security.auth.WSSubject;
import com.ibm.websphere.security.auth.callback.WSCallbackHandlerImpl;
import commonj.sdo.DataObject;


/**
 * Servlet implementation class VMMTestServlet
 */
@WebServlet("/VMMTestServlet")
public class VMMTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	 // Virtual member manager service that is used to make API calls
    static Service service = null;
    /**
     * Locates virtual member manager service in local JVM
     **/
    public static Service locateService()
    {
        try { 
            // Local access virtual member manager Service 
            return new LocalServiceProvider(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

 
    /**
     * Runs action as specified user
     * @param user user name
     * @param password password of the user
     * @param action Action to invoke after successful login of the user
     * @return Object returned by the action
     **/
    public static Object runAsUser(String user, String password, PrivilegedExceptionAction action) throws Exception
    {
        LoginContext loginContext;
        Subject subject;

        // Login using the userid and password that was passed, which has the required role
        loginContext = new LoginContext("WSLogin", new WSCallbackHandlerImpl(user, "", password));
        loginContext.login();
        subject = loginContext.getSubject();

        try {
            return WSSubject.doAs(subject, action);
        }
        catch (PrivilegedActionException excp) {
            throw (Exception) excp.getCause();
        }
    }

    public static String printDO(DataObject obj)
    {
        return WIMTraceHelper.printDataObject(obj);
    }

    /**
     * Loop through the entities in the DataObject and print its uniqueName
     * @param root input DataObject
     */
    @SuppressWarnings("unchecked")
    public static void printIdentifiers(DataObject root) throws Exception
    {
        // Get all entities in the DataObject
        List entities = root.getList(SchemaConstants.DO_ENTITIES);
        for (int i = 0; i < entities.size(); i++) {
            DataObject ent = (DataObject) entities.get(i);
            // Get the entity Identifier
            DataObject id = ent.getDataObject(SchemaConstants.DO_IDENTIFIER);
            if (id != null) {
                String uniqueName = id.getString(SchemaConstants.PROP_UNIQUE_NAME);
                System.out.println("UniqueName is  -> " +uniqueName);
            }
            else {
                System.out.println("Missing Identifier");
            }
        }
    }
    
    /**
     *  This test builds a search filter, calls a search API,
     *  and parses the search results
     */
    @SuppressWarnings("unchecked")
    public static void testSearch() throws Exception
    {
    	
    	//  DataObject result = (DataObject) runAsUser(user, password, new java.security.PrivilegedExceptionAction()
    	service = locateService();
    	DataObject result = (DataObject) runAsUser("waslocal", "waslocal", new java.security.PrivilegedExceptionAction()
        {
            public Object run() throws Exception
            {
                //Note the service instance used is that of security domain obtained in step 1.
                DataObject root = service.createRootDataObject();
                DataObject searchCtrl = SDOHelper.createControlDataObject(root, null, SchemaConstants.DO_SEARCH_CONTROL);
                // Set the properties that need to be retrieved in search results
                searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("uid");
                searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("cn"); 
                searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("sn"); 
                //searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("telephoneNumber");  
                // Set the search base in which search will be performed
                searchCtrl.getList(SchemaConstants.PROP_SEARCH_BASES).add("o=defaultWIMFileBasedRealm");
                // Set the search filter as uid, here the expression will search for all uids
                searchCtrl.setString(SchemaConstants.PROP_SEARCH_EXPRESSION, "@xsi:type='PersonAccount' and uid='*'");
                System.out.println("Input datagraph before searching for users in the searchbase"+ printDO(root));  
                DataObject retObject = service.search(root);
                // Print the output datagraph
                System.out.println("Output datagraph after creating user" + printDO(retObject));
                return retObject;
            }
        });
    	
    	/**
        DataObject root = SDOHelper.createRootDataObject();
        DataObject searchCtrl = SDOHelper.createControlDataObject(root, null, SchemaConstants.DO_SEARCH_CONTROL);
        // Set the properties that need to be retrieved in search results
        searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("uid");
        searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("cn"); 
        searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("sn"); 
        //searchCtrl.getList(SchemaConstants.PROP_PROPERTIES).add("telephoneNumber");  
        // Set the search base in which search will be performed
        searchCtrl.getList(SchemaConstants.PROP_SEARCH_BASES).add("o=defaultWIMFileBasedRealm");
        // Set the search filter as uid, here the expression will search for all uids
        searchCtrl.setString(SchemaConstants.PROP_SEARCH_EXPRESSION, "@xsi:type='PersonAccount' and uid='*'");
        System.out.println("Input datagraph before searching for users in the searchbase"+ printDO(root));  
        
        
        DataObject result = locateService().search(root);
        System.out.println("Output datagraph after searching for users in the searchbase"+ printDO(result));
        // Loop through the search results and print the uniqueName
        **/
        printIdentifiers(result);
    }
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VMMTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
		response.getWriter().println("TEST IS WORKING!!!");
		response.getWriter().println(locateService());
		try {
			testSearch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
