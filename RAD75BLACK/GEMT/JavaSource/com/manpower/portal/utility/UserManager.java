/*
 * Created on Feb 22, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.portlet.service.PortletServiceUnavailableException;
import com.ibm.portal.puma.Group;
import com.ibm.portal.um.Principal;
import com.ibm.portal.um.PumaController;
import com.ibm.portal.um.PumaHome;
import com.ibm.portal.um.PumaLocator;
import com.ibm.portal.um.PumaProfile;
import com.ibm.portal.um.User;
import com.ibm.portal.um.exceptions.PumaException;
/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserManager {

	public static String ACTION_SUCCESS = "SUCCESS";
	public static String ACTION_FAIL	= "FAIL";
	private PumaHome service;
	private PortletServiceHome psh;
	private HttpServletRequest request;
		
	public UserManager(FacesContext context) throws PortletServiceUnavailableException, NamingException{
		
		javax.naming.Context ctx = new javax.naming.InitialContext();
		
		this.psh = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.um.portletservice.PumaHome");
		this.service = (PumaHome) psh.getPortletService(PumaHome.class);
		this.request = (HttpServletRequest) context.getExternalContext().getRequest();
		
	}
	
	public UserManager(HttpServletRequest request) throws PortletServiceUnavailableException, NamingException{
		this.request = request;
		javax.naming.Context ctx = new javax.naming.InitialContext();
		this.psh = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.um.portletservice.PumaHome");
		this.service = (PumaHome) psh.getPortletService(PumaHome.class);
		this.request=request;
		
	}
	public User getCurrentUser(){
		
		PumaProfile pp=service.getProfile(request);
		User user = null;
		try {
			user = pp.getCurrentUser();
			
		} catch (PumaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public Map getCurrentUserAttributes(){
		PumaProfile pp=service.getProfile(request);
		
		User user = null;
		Map map = new HashMap();
		try {
			user = pp.getCurrentUser();
			List definedAttributes = pp.getDefinedUserAttributeNames();
			map = pp.getAttributes(user,definedAttributes);
		} catch (PumaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	}
	
	
	public boolean userExists(String attribName,String userEmail){
		PumaLocator pl = service.getLocator(request);
	
		boolean exists = false;
		try{
			exists=pl.findUsersByAttribute(attribName,userEmail).size()>0;
		}catch (Exception e){
			//DO NOTHING
		}
		return exists;
	}
	public boolean groupExists(String groupName){
		PumaLocator pl = service.getLocator(request);
		boolean exists = false;
		try{
			exists=pl.findGroupsByAttribute("cn",groupName).size()>0;
		}catch (Exception e){
			//DO NOTHING
		}
		return exists;
	}
	
	
	
	public List getPrincipal(String userEmail){
		PumaLocator pl = service.getLocator(request);
		List principalList = null;
		try{
			principalList=pl.findUsersByAttribute("cn",userEmail);
		}catch (Exception e){
			e.printStackTrace();
		}
		return principalList;
	}
	
	public List getPrincipalByAttribute(String attrib,String attribValue){
		PumaLocator pl = service.getLocator(request);
		List principalList=null;
		try{
			principalList = pl.findUsersByAttribute(attrib, attribValue);
		}catch (Exception e){
			e.printStackTrace();
		}
		return principalList;
	}
	
	public List getAllUsersFromGroup(String groupName){
		PumaLocator pl = service.getLocator(request);
		List groupList = null;
		
		try{
			Group group = null;
			groupList=pl.findGroupsByAttribute("cn",groupName);
			if (groupList.size()>0){
				group=(Group) groupList.get(0);
			}
			if (group.getMembers().size()>0){
				groupList=group.getMembers();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return groupList;
	}
	public Group getGroup(String groupName){
		PumaLocator pl = service.getLocator(request);
		List groupList = null;
		Group group = null;
		try{
			groupList=pl.findGroupsByAttribute("cn",groupName);
			if (groupList.size()>0){
				group=(Group) groupList.get(0);
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return group;
	}
	
	public List findUsersByLocality(String localityName){
		PumaLocator pl = service.getLocator(request);
		List groupList = null;
		try{
			groupList=pl.findUsersByAttribute("localityName", localityName+"*");
		}catch(Exception e){
			e.printStackTrace();
		}
		return groupList;
	}
	public List findUsersByLocality(String [] localityNames){
		PumaLocator pl = service.getLocator(request);
		List groupList = new ArrayList();
		try{
			for (int i = 0;i<localityNames.length;i++){
				groupList.addAll(pl.findUsersByAttribute("localityName", localityNames[i]+"*"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return groupList;
	}
	
	public User findUserByUid(String uid){
		PumaLocator pl = service.getLocator(request);
		List groupList = null;
		User user = null;
		try{
			groupList=pl.findUsersByAttribute("uid", uid);
			if (groupList.size()>0)user=(User)groupList.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public String deleteUser(String userEmail){
		String result = ACTION_FAIL;
		try{	          
	          if (psh != null){  
	             PumaController pc = service.getController(request);
	             PumaLocator pl = service.getLocator(request);
	             
	             List list = pl.findUsersByAttribute("cn",userEmail);
	             if (list.size() > 0 ){
		             User user = (User)list.get(0);
		             pc.deleteUser(user);
		             result = ACTION_SUCCESS;
	             } 
	          }
	       } catch (Exception e) {
	       		e.printStackTrace();
	       }
		
		return result;
	}
	
	public String updatePassword(String useremail,String password){
		String result = ACTION_FAIL;			
        try{
           if (psh != null){
             if (userExists("uid",useremail)){
             	 PumaController pc = service.getController(request);
             	 
             	List principalList = getPrincipalByAttribute("uid",useremail);
             	Principal p = null;
             	if (principalList.size()>0)
             	{
             		 p = (Principal)principalList.get(0);
             	}
                 Map map = new HashMap();
                 map.put("userPassword",password);   
                 pc.setAttributes(p , map); 
                 result = ACTION_SUCCESS;
             }
           }
        }
           catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}

	public void removeUserFromGroup(User user,String groupname){
		PumaController pc = service.getController(request);
		Principal principal = (Principal)user;
		List list = new ArrayList();
		list.add(principal);
		try {
			pc.removeFromGroup(getGroup(groupname),list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addUserToGroup(User user, String groupname){
		PumaController pc = service.getController(request);
		Principal principal = (Principal)user;
		List list = new ArrayList();
		list.add(principal);
		try {
			pc.addToGroup(getGroup(groupname),list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isMemberOf(User user,String groupName){
		boolean ismember = false;
		Principal principal = (Principal)user;
		PumaLocator pl = service.getLocator(request);
		
		try {
			List groups = (List)pl.findGroupsByPrincipal(principal, true);
			for (int i = 0;i<groups.size();i++){
				Group g = (Group)groups.get(i);
				if (groupName.equals(g.get("cn").toString())){
					ismember = true;
					break;
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return ismember;
	}

}
