package com.manpower.ldap.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

public class UserAccessWS {
	
	private static Logger log= Logger.getLogger(UserAccessWS.class);
	
	private static final String PARENT_DN = "OU=users,OU=global,O=manpower";
	
	private static final String GROUP_DN = "OU=SecureSelfService,O=manpower";
	
	public static final String USA_COUNTRY = "USA";
	
	public static final String CANADA_COUNTRY = "Canada";
	
	private LDAPConnection conn;
	
	public UserAccessWS()
	{
		conn = new LDAPConnection();
	}
	
	private boolean connectTo(String host, int port, String loginDN,
            String loginPassword)
    {
        boolean result = false;

        try
        {
            conn.connect(host, port);

            conn.bind(3, loginDN, loginPassword.getBytes());
        }
        catch (LDAPException e)
        {
            e.printStackTrace();
        }

        return result;
    }
	
	private void disconnect()
    {
        try
        {
            // disconnect with the server
            conn.disconnect();
        }
        catch (LDAPException e)
        {
            log.error("Error:  " + e.toString());
        }
    }
	
	private String addEntry(String cn, String uid, 
			String parentDN, String sn)
	{
		String dn = "CN=" + cn + "," + parentDN;
		
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();
        
        /* To Add an entry to the directory,
         *  - Create the attributes of the entry and add them to an attribute set
         *  - Specify the DN of the entry to be created
         *  - Create an LDAPEntry object with the DN and the attribute set
         */
        attributeSet.add(new LDAPAttribute("objectclass", new String(
                "inetOrgPerson")));
        attributeSet.add(new LDAPAttribute("objectclass", new String(
                "organizationalPerson")));
        attributeSet
                .add(new LDAPAttribute("objectclass", new String("person")));
        attributeSet.add(new LDAPAttribute("objectclass", new String("top")));

        attributeSet.add(new LDAPAttribute("cn", cn));
        attributeSet.add(new LDAPAttribute("uid", uid));
        attributeSet.add(new LDAPAttribute("sn", sn));
//        attributeSet.add(new LDAPAttribute("userpassword", userPassword));
        
        System.out.println("Adding object: " + dn + " ...");
        
        LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
        
        try
        {
            conn.add(newEntry);
            System.out.println("Added object: " + dn + " successfully.");
        }
        catch (LDAPException e)
        {
        	System.out.println("Error:  " + e.toString());

            return "";
        }
        
		return dn;
	}
	
	private String createOrganizationalUnit(String ou, String parentDN)
	{
		String dn = "OU=" + ou + "," + parentDN;
		
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();
        
        /* To Add an entry to the directory,
         *  - Create the attributes of the entry and add them to an attribute set
         *  - Specify the DN of the entry to be created
         *  - Create an LDAPEntry object with the DN and the attribute set
         */
        
        
        attributeSet.add(new LDAPAttribute("instanceType", "4"));
        
        attributeSet
        .add(new LDAPAttribute("objectclass", new String("organizationalUnit")));
        attributeSet.add(new LDAPAttribute("objectclass", new String("top")));
        
//        attributeSet.add(new LDAPAttribute("auxiliaryClass", new String("msDS-BindableObject")));
        
        System.out.println("Adding object: " + dn + " ...");
        
        LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
        
        try
        {
            conn.add(newEntry);
            System.out.println("Added object: " + dn + " successfully.");
        }
        catch (LDAPException e)
        {
        	System.out.println("Error:  " + e.toString());

            return "";
        }
        
		return dn;
	}
	
	private void delete(String deleteDN)
	{
		System.out.println("Deleting entry");
		try {
			conn.delete(deleteDN);
		} catch (LDAPException e) {
			System.out.println("Exception while deleting:" + e.getMessage());
			return;
		}
		
		System.out.println("Deleted");
		
	}
	
	private String addContainerEntry(String cn, String parentDN)
	{
		String dn = "CN=" + cn + "," + parentDN;
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();
		
		attributeSet.add(new LDAPAttribute("cn", cn));
		attributeSet
        .add(new LDAPAttribute("objectclass", new String("container")));
        attributeSet.add(new LDAPAttribute("objectclass", new String("top")));
		
        System.out.println("Adding object: " + dn + " ...");
        
        LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
        
        try
        {
            conn.add(newEntry);
            System.out.println("Added object: " + dn + " successfully.");
        }
        catch (LDAPException e)
        {
        	System.out.println("Error:  " + e.toString());

            return "";
        }
        
		return dn;
	}
	
	private String addGroupEntry(String cn, String parentDN)
	{
		String dn = "CN=" + cn + "," + parentDN;
		
        LDAPAttributeSet attributeSet = new LDAPAttributeSet();
        
        /* To Add an entry to the directory,
         *  - Create the attributes of the entry and add them to an attribute set
         *  - Specify the DN of the entry to be created
         *  - Create an LDAPEntry object with the DN and the attribute set
         */
        
        
        attributeSet.add(new LDAPAttribute("groupType", "4"));
        attributeSet.add(new LDAPAttribute("instanceType", "4"));

        attributeSet.add(new LDAPAttribute("cn", cn));
        
        attributeSet
        .add(new LDAPAttribute("objectclass", new String("group")));
        attributeSet.add(new LDAPAttribute("objectclass", new String("top")));
        
//        attributeSet.add(new LDAPAttribute("auxiliaryClass", new String("msDS-BindableObject")));
        
        System.out.println("Adding object: " + dn + " ...");
        
        LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
        
        try
        {
            conn.add(newEntry);
            System.out.println("Added object: " + dn + " successfully.");
        }
        catch (LDAPException e)
        {
        	System.out.println("Error:  " + e.toString());

            return "";
        }
        
		return dn;
	}
	
	private boolean addUserToGroup(String userdn, String groupDN)
	{
		boolean added = addUserToGroup(conn, userdn, groupDN);
		
		return added;
	}
	private boolean addUserToGroup(LDAPConnection lc, String userdn,
            String groupDN)
    {

        // modifications for group and user
        LDAPModification modGroup = new LDAPModification();

        // Add modifications to modGroup
        LDAPAttribute member = new LDAPAttribute("member", userdn);
        modGroup = new LDAPModification(LDAPModification.ADD, member);

        try
        {
            // Modify the group's attributes
            lc.modify(groupDN, modGroup);
            System.out.println("Modified user's group membership to: "+groupDN);
        }
        catch (LDAPException e)
        {
        	System.out.println("Failed to modify group's attributes: "
                    + e.toString());

            return false;
        }
        return true;
    }
	
	
	private boolean removeUserFromGroup(LDAPConnection lc, String userdn,
            String groupDN)
    {
        // modifications for group and user
        LDAPModification modGroup = new LDAPModification();

        // Add modifications to modGroup
        LDAPAttribute member = new LDAPAttribute("member", userdn);
        modGroup = new LDAPModification(LDAPModification.DELETE, member);
        
        try
        {
            // Modify the group's attributes
            lc.modify(groupDN, modGroup);
            log.debug("Modified user's group membership to: "+groupDN);
        }
        catch (LDAPException e)
        {
            log.error("Failed to modify group's attributes: "
                    + e.toString());
            
            return false;
        }
        return true;
    }
	
	private boolean deleteUser(String userdn,String groupDN)
	{
		boolean result = deleteUserToGroup(conn, userdn, groupDN);
		
		return result;
	}
	
	private boolean deleteUserToGroup(LDAPConnection lc, String userdn,
            String groupDN)
    {

        // modifications for group and user
        LDAPModification modGroup = new LDAPModification();

        // Add modifications to modGroup
        LDAPAttribute member = new LDAPAttribute("member", userdn);
        modGroup = new LDAPModification(LDAPModification.DELETE, member);

        try
        {
            // Modify the group's attributes
            lc.modify(groupDN, modGroup);
            log.debug("Modified user's group membership to: "+groupDN);
        }
        catch (LDAPException e)
        {
            log.error("Failed to modify group's attributes: "
                    + e.toString());

            return false;
        }
        return true;
    }
	
	private boolean createUser(String cn, String uid, String country, String sn)
    {
        boolean result = false;
        
        String groupDN = "CN=" + country + "," + GROUP_DN;
        
        //create entry
        String userDN = addEntry(cn, uid, PARENT_DN, sn);

        //add the entry to a group
        if(userDN != null && (userDN = userDN.trim()).length() > 0)
        {               
        	System.out.println("Adding user to group...");
            result=addUserToGroup(conn, userDN, groupDN);
            System.out.println("User added");
        }

        return result;
    }

	public boolean grantRemoveAccess(long candidateId, String country, boolean grantAccess) throws Exception
	{
		connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		String userDN = "CN=" + candidateId + "," + PARENT_DN;
		String groupDN = "CN=" + country + "," + GROUP_DN;
		if(grantAccess)
		{
			
			if(isUserExist(candidateId))
			{
				
				if(!isUserMemmber(country, candidateId))
				{
					addUserToGroup(userDN, groupDN);
				}
				disconnect();
				return true;
			}
		}
		else
		{
			if(isUserExist(candidateId))
			{
				removeUserFromGroup(conn, userDN, groupDN);
			}
			
		}
		disconnect();
		return grantAccess;
	}
	
	private boolean isUserMemmber(String country, long candidateId)
	{
		String searchBase = "CN=" + country + "," + GROUP_DN;
		
		List attrs = search(searchBase, LDAPConnection.SCOPE_BASE, "(objectclass=*)", new String[]{"member"});
		
		if(attrs != null)
		{
			System.out.println("size = " + attrs.size());
			for(int i = 0; i < attrs.size(); i++)
			{
				String ldapAttr = (String)attrs.get(i);
				System.out.println("ldapAttr = " + ldapAttr);
				int index = ldapAttr.indexOf("" + candidateId);
				if(index != -1)
				{
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	private boolean isUserExist(long candidateId)
	{
		String searchDN = "CN=" + candidateId + "," + PARENT_DN;
		
		System.out.println("searchDN = " + searchDN);
		
		LDAPSearchResults results = null;
		
		
		try {
			
			results = conn.search(PARENT_DN, LDAPConnection.SCOPE_ONE, "(objectclass=*)", null, false);
			while(results.hasMore())
			{
				LDAPEntry ldapEntry = results.next();
				String foundDN = ldapEntry.getDN();
				
				if(foundDN.equalsIgnoreCase(searchDN))
				{
					return true;
				}
			}
		} catch (LDAPException e) {
			log.error("Exception", e);
			System.out.println("Exception"+ e.getMessage());
		}
		
		return false;
	}
		
	public boolean hasAccess_USA(long candidateId) throws Exception
	{
		log.debug("UserAccessWS.hasAccess_USA - ENTRY");
		connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		if(!isUserExist(candidateId))
		{
			disconnect();
			log.debug("User " + candidateId + " does not exist. Return false");
			log.debug("UserAccessWS.hasAccess_USA - EXIT");
			return false;
		}
		log.debug("Check if user " + candidateId + " is member");
		if(isUserMemmber(USA_COUNTRY, candidateId))
		{
			log.debug("User is member. Return true");
			log.debug("UserAccessWS.hasAccess_USA - EXIT");
			disconnect();
			return true;
		}
		log.debug("User is not member. Return false;");
		log.debug("UserAccessWS.hasAccess_USA - EXIT");
		disconnect();
		return false;
	}
	
	public boolean hasAccess_Canada(long candidateId) throws Exception
	{
		log.debug("UserAccessWS.hasAccess_Canada - ENTRY");
		log.debug("Connect to LDAP server");
		connectTo("gsd1w124s.manpower.com", 50000, "CN=wpsbind,OU=users,OU=global,O=manpower", "wpsadmin");
		log.debug("Check if user " + candidateId + " exists");
		if(!isUserExist(candidateId))
		{
			log.debug("User does not exist. Return false");
			log.debug("UserAccessWS.hasAccess_Canada - EXIT");
			disconnect();
			return false;
		}
		log.debug("Check if user is member");
		if(isUserMemmber(CANADA_COUNTRY, candidateId))
		{
			log.debug("User is member of the country group. Return true");
			log.debug("UserAccessWS.hasAccess_Canada - EXIT");
			disconnect();
			return true;
		}
		log.debug("Return false");
		log.debug("UserAccessWS.hasAccess_Canada - EXIT");
		disconnect();
		return false;
	}
	
	
	private List search(String searchBase, int searchScope, String filter,
            String[] attrList)
    {
        List results = new ArrayList();

        try
        {
            LDAPSearchResults searchRS = conn.search(searchBase, searchScope,
                    filter, attrList, false);

            while (searchRS.hasMore())
            {
                LDAPEntry entry = searchRS.next();
                log.debug("\n We found " + entry.getDN());

                LDAPAttributeSet attributeSet = entry.getAttributeSet();
                Iterator iter = attributeSet.iterator();

                log.debug("\n We found " + attributeSet.size()
                        + " attributes.");

                if(iter.hasNext())
                {
                	LDAPAttribute attribute = (LDAPAttribute) iter.next();
                	log.debug("\n We found " + attribute.toString()
                            + " attribute.");
                    String[] members = attribute.getStringValueArray();
                    for(int i = 0; i < members.length; i++)
                    {
                    	results.add(members[i]);
                    }
                }
                                
            }
        }
        catch (LDAPException e)
        {
            e.printStackTrace();
        }

        return results;
    }
}
