package com.asponte.vmm;

import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

import com.ibm.websphere.security.auth.WSSubject;
import com.ibm.websphere.security.auth.callback.WSCallbackHandlerImpl;
import com.ibm.websphere.wim.SchemaConstants;
import com.ibm.websphere.wim.ras.WIMTraceHelper;
import commonj.sdo.DataObject;

public class VMMBase {
	 // Virtual member manager service that is used to make API calls
   
    /**
     * Runs action as specified user
     * @param user user name
     * @param password password of the user
     * @param action Action to invoke after successful login of the user
     * @return Object returned by the action
     **/
    public static Object runAsUser(String user, String password, PrivilegedExceptionAction<?> action) throws Exception
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

    public static void printIdentifiers(DataObject root) throws Exception
    {
        // Get all entities in the DataObject
        List<?> entities = root.getList(SchemaConstants.DO_ENTITIES);
        for (int i = 0; i < entities.size(); i++) {
            DataObject ent = (DataObject) entities.get(i);
            // Get the entity Identifier
            DataObject id = ent.getDataObject(SchemaConstants.DO_IDENTIFIER);
            if (id != null) {
                String uniqueName = id.getString(SchemaConstants.PROP_UNIQUE_NAME);
//                System.out.println("UniqueName is  -> " +uniqueName);
            }
            else {
                System.out.println("Missing Identifier");
            }
        }
    }
}
